package com.example.taskids.models



data class TaskModel(
    val id: Int = 0, // ✅ Default value to avoid uninitialized variables
    var title: String = "",
    var description: String = "",
    var assigned_to: Int? = null, // ✅ Nullable for unassigned tasks
    var completed: Boolean = false, // ✅ Default false (task starts as incomplete)
    var points: Int
)