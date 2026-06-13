package com.example.labo4_pdm.ui.Screens.CreateTaskScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labo4_pdm.Model.Task
import com.example.labo4_pdm.viewModel.GeneralViewModel
import kotlin.random.Random

@Composable
fun CreateTaskScreen(viewModel: GeneralViewModel, onNavigateBack: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(value = title, onValueChange = { title = it }, label = { Text("Título") })
        TextField(value = description, onValueChange = { description = it }, label = { Text("Descripción") })
        TextField(value = endDate, onValueChange = { endDate = it }, label = { Text("Fecha de Vencimiento") })

        Button(
            onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    // Dentro del onClick de tu botón:
                    val newTask = Task(
                        id = 0, // 0 indica a Room que genere el ID automáticamente
                        title = title,
                        description = description,
                        endDate = endDate,
                        isCompleted = false
                    )
                    viewModel.addTask(newTask)
                    onNavigateBack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Tarea")
        }
    }
}