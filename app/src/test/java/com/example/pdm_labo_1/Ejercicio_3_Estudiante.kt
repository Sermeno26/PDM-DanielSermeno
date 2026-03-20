package com.example.pdm_labo_1

import org.junit.Test
data class Estudiante(
    val nombre: String,
    val carnet: String,
    val asignatura: String
)

class EstudianteTest {

    @Test
    fun Estudiantes() {

        val Ciclo01 = listOf(
            Estudiante("Daniel", "00030022", "Programacion de Dispositivos Moviles"),
            Estudiante("Chen", "00023123", "Programacion de Dispositivos Moviles"),
            Estudiante("Jose Luis", "00024123", "Programacion de Dispositivos Moviles"),

            Estudiante("Herberth", "00033123", "Analisis Numerico"),
            Estudiante("Otto", "00034123", "Analisis Numerico"),
            Estudiante("Pedro", "00123123", "Analisis Numerico"),
            Estudiante("Sofia", "02023123", "Analisis Numerico")
        )

        val moviles = Ciclo01.filter {
            it.asignatura == "Programacion de Dispositivos Moviles"
        }

        println("Estudiantes de Dispositivos Moviles:")

        moviles.forEach {
            println("${it.nombre} - ${it.carnet}")
        }
    }
}
