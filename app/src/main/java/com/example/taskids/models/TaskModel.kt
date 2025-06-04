package com.example.taskids.models



data class TaskModel(
    val id: Int = 0,
    var title: String = "",
    var description: String = "",
    var assigned_to: Int? = null,
    var completed: Boolean = false,
    var points: Int
)