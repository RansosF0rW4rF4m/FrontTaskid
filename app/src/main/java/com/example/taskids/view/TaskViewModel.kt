package com.example.taskids.view
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskids.models.TaskModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.State
import com.example.taskids.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _tasks = mutableStateOf<List<TaskModel>>(emptyList())
    val tasks: State<List<TaskModel>> = _tasks

    private val _selectedTask = mutableStateOf<TaskModel?>(null)
    val selectedTask: State<TaskModel?> = _selectedTask

    init {
        fetchTasks()
    }

    fun fetchTasks() {
        viewModelScope.launch {
            _tasks.value = repository.getAllTasks()
        }
    }

    fun fetchTaskById(id: Int) {
        viewModelScope.launch {
            _selectedTask.value = repository.getTaskById(id)
        }
    }

    fun createTask(task: TaskModel) {
        viewModelScope.launch {
            val success = repository.createTask(task)
            if (success) fetchTasks()
        }
    }

    fun updateTask(id: Int, task: TaskModel) {
        viewModelScope.launch {
            val success = repository.updateTask(id, task)
            if (success) fetchTasks()
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch {
            val success = repository.deleteTask(id)
            if (success) fetchTasks()
        }
    }
}
