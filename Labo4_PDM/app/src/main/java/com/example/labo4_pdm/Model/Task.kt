package com.example.labo4_pdm.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Room generará el ID automáticamente
    val title: String,
    val description: String,
    val endDate: String,
    val isCompleted: Boolean
)