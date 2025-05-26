package com.example.taskids.models

data class TaskModel(
    val id: Int? = null,
    val title: String,
    val description: String? = null,
    val status: String? = null, // ex: "pendente", "validada"
    val score: Int? = null,
    val beforeImageUrl: String? = null,
    val afterImageUrl: String? = null
)
