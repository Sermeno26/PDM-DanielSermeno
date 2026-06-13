package com.example.labo4_pdm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labo4_pdm.InitDatabase
import com.example.labo4_pdm.Model.Task
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GeneralViewModel : ViewModel() {

    // Obtenemos el DAO desde nuestra clase Application
    private val taskDao = InitDatabase.database.taskDao()

    // Conectamos el Flow de Room directamente al estado de la UI
    val tasks: StateFlow<List<Task>> = taskDao.getAllTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addTask(task: Task) {
        // Las inserciones en base de datos deben ir en una corrutina (viewModelScope)
        viewModelScope.launch {
            taskDao.insertTask(task)
        }
    }
}