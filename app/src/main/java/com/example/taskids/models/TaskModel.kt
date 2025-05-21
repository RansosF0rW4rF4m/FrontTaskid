package com.example.taskids.models

data class TaskModel(
    var id: Int,
    var title: String? = null,
    var description: String? = null,
    var childId: Int? = null
)