package edu.unicauca.aplimovil.masccotas_v1

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.unicauca.aplimovil.masccotas_v1.Registro.RegisterScreen
import edu.unicauca.aplimovil.masccotas_v1.Registro.RegisterViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    registerViewModel: RegisterViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "register"
    ) {
        composable("register") {
            RegisterScreen(
                viewModel = registerViewModel,
                navController = navController
            )
        }

        // Agrega aquí otras pantallas cuando las tengas
        // composable("home") { HomeScreen(...) }
    }
}