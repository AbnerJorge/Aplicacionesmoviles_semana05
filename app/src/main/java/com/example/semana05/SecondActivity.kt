package com.example.semana05

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.semana05.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Opciones para el spinner (soles a dólares, y viceversa)
        val opcionesDivisas = arrayOf("Soles a Dólares", "Dólares a Soles")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesDivisas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerDivisas.adapter = adapter

        // Configuramos la acción del botón para realizar la conversión
        binding.btnConvertir.setOnClickListener {
            val montoStr = binding.editTextMonto.text.toString()

            // Verificamos si el monto es válido
            if (montoStr.isNotEmpty()) {
                val monto = montoStr.toDouble()

                // Obtenemos la divisa seleccionada
                val divisaSeleccionada = binding.spinnerDivisas.selectedItem.toString()

                // Realizamos la conversión según la divisa seleccionada
                val resultado = when (divisaSeleccionada) {
                    "Soles a Dólares" -> convertirSolesADolares(monto)
                    "Dólares a Soles" -> convertirDolaresASoles(monto)
                    else -> 0.0
                }

                // Mostramos el resultado
                binding.textViewResultado.text = "Resultado: %.2f".format(resultado)
            } else {
                Toast.makeText(this, "Por favor, ingrese un monto válido", Toast.LENGTH_SHORT).show()
            }
        }

        // Configuramos el botón logout
        binding.btnLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
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