package com.example.aplicacioncalculadora

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // Variables para manejar la lógica de la calculadora
    private var resultado: Double = 0.0
    private var operacionPendiente: String? = null
    private var valorActual: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textRespuesta = findViewById<TextView>(R.id.textRespuesta)
        val btnLimpiar = findViewById<Button>(R.id.btn_limpiar)
        val btnCalcular = findViewById<Button>(R.id.btn_calcular)

        // Número buttons
        val btn0 = findViewById<Button>(R.id.btn0)
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)

        // Operación buttons
        val btnSum = findViewById<Button>(R.id.btn_sum)
        val btnResta = findViewById<Button>(R.id.btn_resta)
        val btnMulti = findViewById<Button>(R.id.btn_multi)
        val btnDiv = findViewById<Button>(R.id.btn_div)

        // Función para añadir el número al valor actual
        fun agregarNumero(num: String) {
            valorActual += num
            textRespuesta.text = valorActual
        }

        // Asignar clics para los botones numéricos
        val numberButtons = listOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
        numberButtons.forEach { button ->
            button.setOnClickListener {
                agregarNumero(button.text.toString())
            }
        }

        // Función para manejar las operaciones
        fun realizarOperacion(operador: String) {
            if (valorActual.isNotEmpty()) {
                if (operacionPendiente == null) {
                    resultado = valorActual.toDouble()
                } else {
                    when (operacionPendiente) {
                        "+" -> resultado += valorActual.toDouble()
                        "-" -> resultado -= valorActual.toDouble()
                        "*" -> resultado *= valorActual.toDouble()
                        "/" -> resultado /= valorActual.toDouble()
                    }
                }
                operacionPendiente = operador
                valorActual = ""
                textRespuesta.text = resultado.toString()
            }
        }

        // Asignar clics para las operaciones
        btnSum.setOnClickListener { realizarOperacion("+") }
        btnResta.setOnClickListener { realizarOperacion("-") }
        btnMulti.setOnClickListener { realizarOperacion("*") }
        btnDiv.setOnClickListener { realizarOperacion("/") }

        // Botón para calcular el resultado final
        btnCalcular.setOnClickListener {
            realizarOperacion(operacionPendiente ?: "")
            operacionPendiente = null
        }

        // Botón para limpiar la calculadora
        btnLimpiar.setOnClickListener {
            resultado = 0.0
            valorActual = ""
            operacionPendiente = null
            textRespuesta.text = "0"
        }
    }
}
