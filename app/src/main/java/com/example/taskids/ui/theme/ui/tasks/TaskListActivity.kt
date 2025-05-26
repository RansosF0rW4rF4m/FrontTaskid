package com.example.taskids.ui.theme.ui.tasks

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class TaskListActivity : AppCompatActivity() {

    private val repo = TaskRepository()
    private lateinit var token: String

    private val PICK_BEFORE_IMAGE_REQUEST = 101
    private val PICK_AFTER_IMAGE_REQUEST = 102

    private var selectedTaskIdForUpload: Int? = null
    private var isBeforeImageUpload = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        token = getSharedPreferences("app", MODE_PRIVATE).getString("token", "") ?: ""

        val btnLoadTasks = findViewById<Button>(R.id.btnLoadTasks)
        btnLoadTasks.setOnClickListener {
            loadTasks()
        }

        val btnUploadBefore = findViewById<Button>(R.id.btnUploadBefore)
        btnUploadBefore.setOnClickListener {
            selectedTaskIdForUpload = 1 // aqui fixo só para exemplo, deve ser selecionado da lista
            isBeforeImageUpload = true
            pickImageFromGallery()
        }

        val btnUploadAfter = findViewById<Button>(R.id.btnUploadAfter)
        btnUploadAfter.setOnClickListener {
            selectedTaskIdForUpload = 1
            isBeforeImageUpload = false
            pickImageFromGallery()
        }
    }

    private fun loadTasks() {
        lifecycleScope.launch {
            val response = repo.getTasks(token)
            if (response.isSuccessful) {
                val tasks = response.body() ?: emptyList()
                Toast.makeText(this@TaskListActivity, "Tarefas carregadas: ${tasks.size}", Toast.LENGTH_SHORT).show()
                // Aqui você deve popular uma ListView ou RecyclerView
            } else {
                Toast.makeText(this@TaskListActivity, "Erro ao carregar tarefas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, if (isBeforeImageUpload) PICK_BEFORE_IMAGE_REQUEST else PICK_AFTER_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            val uri = data.data ?: return
            val file = uriToFile(uri)
            val taskId = selectedTaskIdForUpload ?: return

            lifecycleScope.launch {
                val response = if (requestCode == PICK_BEFORE_IMAGE_REQUEST) {
                    repo.uploadBeforeImage(token, taskId, file)
                } else {
                    repo.uploadAfterImage(token, taskId, file)
                }
                if (response.isSuccessful) {
                    Toast.makeText(this@TaskListActivity, "Imagem enviada com sucesso", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@TaskListActivity, "Falha ao enviar imagem", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun uriToFile(uri: Uri): File {
        val returnCursor = contentResolver.query(uri, null, null, null, null)
        val nameIndex = returnCursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME) ?: 0
        returnCursor?.moveToFirst()
        val name = returnCursor?.getString(nameIndex) ?: "temp_file"
        returnCursor?.close()

        val file = File(cacheDir, name)
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()
        return file
    }
}