package com.example.sumativa2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sumativa2.restaurante.pantalla
import com.example.sumativa2.ui.theme.Sumativa2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sumativa2Theme {
                pantalla()
            }
        }
    }
}