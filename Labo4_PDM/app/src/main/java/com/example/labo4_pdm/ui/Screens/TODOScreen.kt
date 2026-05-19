package com.example.labo4_pdm.ui.Screens.TODOScreen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labo4_pdm.Model.Task
import com.example.labo4_pdm.viewModel.GeneralViewModel

@Composable
fun TODOScreen(viewModel: GeneralViewModel, onNavigateToCreate: () -> Unit) {
    val tasks = viewModel.tasks.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigateToCreate() }) {
                Text("+")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(tasks.value) { task ->
                Log.d("Task", task.toString())

                TaskCard(task = task)
            }
        }
    }
}

@Composable
fun TaskCard(task: Task) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ID: ${task.id}", style = MaterialTheme.typography.labelSmall)
            Text(text = task.title, style = MaterialTheme.typography.titleMedium)
            Text(text = task.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Fecha límite: ${task.endDate}", style = MaterialTheme.typography.bodySmall)
            Text(
                text = if (task.isCompleted) "Completada" else "Pendiente",
                color = if (task.isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        }
    }
}