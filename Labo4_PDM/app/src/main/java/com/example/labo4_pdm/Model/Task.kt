package com.example.labo4_pdm.Model

import java.util.Date

data class Task (
    val id: Int,
    val title: String,
    val description: String,
    val endDate: String = Date(),
    val isCompleted: Boolean = false
)