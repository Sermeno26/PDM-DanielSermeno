package com.example.lab3_pdm

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

data class Persona(var nombre: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            App()
        }
    }
}
@Preview
@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            Ventana(navController)
        }

        composable("sensores") {
            PantallaSensores(navController)
        }
    }
}


@Composable
fun Ventana(navController: NavHostController) {

    var nombre by remember { mutableStateOf("") }
    val listaPersona = remember { mutableStateListOf<Persona>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {

        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Nombre") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (nombre.isNotEmpty()) {
                    listaPersona.add(Persona(nombre))
                    nombre = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                navController.navigate("sensores")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a sensores")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Listado de nombres",
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = { listaPersona.clear() }
            ) {
                Text("Limpiar")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(BorderStroke(2.dp, Color.Blue))
                .padding(8.dp)
        ) {

            LazyColumn {
                itemsIndexed(listaPersona) { index, persona ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(persona.nombre)
                        Text("${index + 1}")
                    }
                }
            }
        }
    }
}

//////////////////////////////////////////////////////////
// 📱 PANTALLA DE SENSORES
//////////////////////////////////////////////////////////

@Composable
fun PantallaSensores(navController: NavHostController) {

    val context = LocalContext.current
    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    var x by remember { mutableFloatStateOf(0f) }
    var y by remember { mutableFloatStateOf(0f) }
    var z by remember { mutableFloatStateOf(0f) }

    var vista by remember { mutableIntStateOf(1) }

    DisposableEffect(Unit) {

        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                x = event.values[0]
                y = event.values[1]
                z = event.values[2]
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        sensorManager.registerListener(
            listener,
            accelerometer,
            SensorManager.SENSOR_DELAY_NORMAL
        )

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text("Sensores", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(20.dp))

        if (vista == 1) {
            Text("Vista 1")
            Text("X: $x")
            Text("Y: $y")
        } else {
            Text("Vista 2")
            Text("Z: $z")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                vista = if (vista == 1) 2 else 1
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cambiar vista")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Regresar")
        }
    }
}