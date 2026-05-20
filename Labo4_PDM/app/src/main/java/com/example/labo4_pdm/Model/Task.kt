package com.example.labo4_pdm.Model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val endDate: String,
    val isCompleted: Boolean = false
)