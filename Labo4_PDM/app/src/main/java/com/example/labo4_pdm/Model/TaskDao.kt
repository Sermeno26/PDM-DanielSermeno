package com.example.labo4_pdm.Model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    // Obtiene todas las tareas y reacciona automáticamente a los cambios gracias a Flow
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Task>>

    // Inserta una nueva tarea. Es 'suspend' porque debe ejecutarse en segundo plano (Corrutina)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task): Long // <-- Agrega ": Long"
}