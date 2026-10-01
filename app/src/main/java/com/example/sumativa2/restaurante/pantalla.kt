package com.example.sumativa2.restaurante


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import com.example.sumativa2.components.textos

@Composable
fun pantalla() {
    var platillo by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var porcentaje by remember { mutableStateOf("") }

    var propina by remember { mutableStateOf(0.0) }
    var total by remember { mutableStateOf(0.0) }
    var mensaje by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Propina: $${"%.2f".format(propina)}", color = Color.Black)
        Text("Total a pagar: $${"%.2f".format(total)}", color = Color.Black)

        textos(platillo, { platillo = it }, "Nombre del platillo")
        textos(cantidad, { cantidad = it }, "Cantidad", KeyboardType.Number)
        textos(precio, { precio = it }, "Precio unitario", KeyboardType.Decimal)
       textos(porcentaje, { porcentaje = it }, "Porcentaje de propina (máx. 10%)", KeyboardType.Decimal)

        Text(mensaje, color = Color.Red)

        Button(
            onClick = {
                val c = cantidad.toIntOrNull()
                val p = precio.toDoubleOrNull()
                val por = porcentaje.toDoubleOrNull()

                if (platillo.isBlank() || cantidad.isBlank() || precio.isBlank() || porcentaje.isBlank()) {
                    mensaje = "Llena todos los campos"
                } else if (c == null || p == null || por == null) {
                    mensaje = "Escribe solo números válidos"
                } else if (c <= 0 || p <= 0 || por < 0) {
                    mensaje = "No uses números negativos ni cero"
                } else if (por > 10) {
                    mensaje = "La propina no puede ser mayor a 10%"
                } else {
                    val subtotal = c * p
                    propina = subtotal * por / 100
                    total = subtotal + propina
                    mensaje = ""
                }
            }
        ) {
            Text("Calcular Total")
        }

        Button(
            onClick = {
                platillo = ""
                cantidad = ""
                precio = ""
                porcentaje = ""
                propina = 0.0
                total = 0.0
                mensaje = ""
            }
        ) {
            Text("Limpiar")
        }
    }
}