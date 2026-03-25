package com.example.DASCUNO.Labo_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


data class Persona(val nombre: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main()
        }
    }
}
@Preview
@Composable
fun Main() {

    var nombre by remember { mutableStateOf("") }
    val listaPersonas = remember { mutableStateListOf<Persona>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Nombre") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if (nombre.isNotEmpty()) {
                        listaPersonas.add(Persona(nombre))
                        nombre = ""
                    }
                }
            ) {
                Text("Guardar")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = {
                listaPersonas.clear()
            }) {
                Text("Limpiar")
            }
        }
        Text("Listado de nombres y posición en la lista")
        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .border(2.dp, Color.Blue)
                .padding(8.dp)
        ) {
            LazyColumn {
                itemsIndexed(listaPersonas) { index, persona ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(persona.nombre)
                        Text((index + 1).toString())
                    }
                }
            }
        }
    }
}