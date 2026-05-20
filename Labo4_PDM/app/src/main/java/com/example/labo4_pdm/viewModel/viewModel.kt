package com.example.labo4_pdm.viewModel

import androidx.lifecycle.ViewModel
import com.example.labo4_pdm.Model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GeneralViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()
    fun addTask(task: Task) {
        _tasks.value = _tasks.value + task
    }
}