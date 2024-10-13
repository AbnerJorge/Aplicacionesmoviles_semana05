package com.example.aplicacionlab3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var editTextMonto: EditText
    private lateinit var spinnerDivisas: Spinner
    private lateinit var buttonConvertir: Button
    private lateinit var textViewResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializamos las vistas
        editTextMonto = findViewById(R.id.editTextMonto)
        spinnerDivisas = findViewById(R.id.spinnerDivisas)
        buttonConvertir = findViewById(R.id.buttonConvertir)
        textViewResultado = findViewById(R.id.textViewResultado)

        // Opciones para el spinner (soles a dólares, y viceversa)
        val opcionesDivisas = arrayOf("Soles a Dólares", "Dólares a Soles")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesDivisas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDivisas.adapter = adapter

        // Configuramos la acción del botón para realizar la conversión
        buttonConvertir.setOnClickListener {
            val montoStr = editTextMonto.text.toString()

            // Verificamos si el monto es válido
            if (montoStr.isNotEmpty()) {
                val monto = montoStr.toDouble()

                // Obtenemos la divisa seleccionada
                val divisaSeleccionada = spinnerDivisas.selectedItem.toString()

                // Realizamos la conversión según la divisa seleccionada
                val resultado = when (divisaSeleccionada) {
                    "Soles a Dólares" -> convertirSolesADolares(monto)
                    "Dólares a Soles" -> convertirDolaresASoles(monto)
                    else -> 0.0
                }

                // Mostramos el resultado
                textViewResultado.text = "Resultado: %.2f".format(resultado)
            } else {
                Toast.makeText(this, "Por favor, ingrese un monto válido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para convertir Soles a Dólares
    private fun convertirSolesADolares(monto: Double): Double {
        val tasaCambio = 0.27
        return monto * tasaCambio
    }

    // Función para convertir Dólares a Soles
    private fun convertirDolaresASoles(monto: Double): Double {
        val tasaCambio = 3.77
        return monto * tasaCambio
    }
}
