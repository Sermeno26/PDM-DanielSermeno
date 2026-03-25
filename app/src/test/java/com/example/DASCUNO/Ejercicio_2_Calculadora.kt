package com.example.DASCUNO
import org.junit.Test
class Calculadora(
    val marca: String,
    val aniosVida: Int,
    var precio: Double
) {

    fun sumar(a: Double, b: Double): Double {
        return a + b
    }

    fun restar(a: Double, b: Double): Double {
        return a - b
    }

    fun multiplicar(a: Double, b: Double): Double {
        return a * b
    }

    fun dividir(a: Double, b: Double): Double {
        if (b == 0.0) {
            println("Error: no se puede dividir entre 0")
            return 0.0
        }
        return a / b}
}

class CalculadoraTest {

    @Test
    fun probarCalculadora() {

        val calc = Calculadora(
            marca = "Texas",
            aniosVida = 7,
            precio = 125.0
        )

        println("Suma: " + calc.sumar(5.0, 3.0))
        println("Resta: " + calc.restar(10.0, 4.0))
        println("Multiplicacion: " + calc.multiplicar(2.0, 6.0))
        println("Division: " + calc.dividir(10.0, 2.0))
        println("Division: " + calc.dividir(0.0,3.0))

        //error
        println("Division con 0: " + calc.dividir(10.0, 0.0))
    }
}

