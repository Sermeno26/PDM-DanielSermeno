package com.example.DASCUNO

import org.junit.Test
class Programa(
    val nombre: String
)

class Computadora(
    var RAM: Int,
    var memoria: Int,
    var SisOperativo: String,
    var programasInstalados: List<Programa>
) {

    fun encender() {
        println("Computadora encendida")
    }

    fun apagar() {
        println("La computadora se ha apagado")
    }

    fun cambiarRAM(nuevaRAM: Int) {
        RAM = nuevaRAM
        println("RAM actualizada a $RAM GB")
    }

    fun cambiarSisOperativo(nuevoSO: String) {
        SisOperativo = nuevoSO
        println("Nuevo SO: $SisOperativo")
    }

    fun ProgramasAnio(anio: String): List<Programa> {
        return programasInstalados.filter { it.nombre.contains(anio) }
    }
}

class ComputadoraTest {

    @Test
    fun probarComputadora() {

        val programas = listOf(
            Programa("notion 2026"),
            Programa("facebook 2024"),
            Programa("WhatsApp 2026"),
            Programa("IG 2026")
        )

        val pc = Computadora(
            8,
            256,
            "Windows 10",
            programas
        )

        pc.encender()
        pc.cambiarRAM(16)

        val resultado = pc.ProgramasAnio("2026")

        println("Programas del 2026:")
        resultado.forEach {
            println(it.nombre)
        }

        pc.apagar()
    }
}
