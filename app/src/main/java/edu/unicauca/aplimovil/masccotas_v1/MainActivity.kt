package edu.unicauca.aplimovil.masccotas_v1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.unicauca.aplimovil.masccotas_v1.Registro.RegisterViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val registerViewModel: RegisterViewModel = viewModel()

            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation(navController, registerViewModel)
                }
            }
        }
    }
}