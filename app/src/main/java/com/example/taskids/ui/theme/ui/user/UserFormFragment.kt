package com.example.taskids.ui.theme.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.compose.foundation.R
import androidx.fragment.app.Fragment
import com.example.taskids.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserFormFragment : Fragment() {

    private lateinit var usernameInput: EditText
    private lateinit var emailInput: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_form, container, false)
        usernameInput = view.findViewById(R.id.usernameInput)
        emailInput = view.findViewById(R.id.emailInput)

        // Exemplo de criação de usuário (poderia ter um botão que chama isso)
        view.findViewById<View>(R.id.saveButton)?.setOnClickListener {
            val user = User(
                username = usernameInput.text.toString(),
                email = emailInput.text.toString(),
                first_name = "",
                last_name = "",
                user_type = "guardian",
                bio = null
            )
            CoroutineScope(Dispatchers.IO).launch {
                UserRepository().createUser(user)
            }
        }

        return view
    }
}