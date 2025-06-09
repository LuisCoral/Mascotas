package edu.unicauca.aplimovil.masccotas_v1

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.unicauca.aplimovil.masccotas_v1.Login.LoginScreen
import edu.unicauca.aplimovil.masccotas_v1.Registro.RegisterScreen
import edu.unicauca.aplimovil.masccotas_v1.Registro.RegisterViewModel
import androidx.compose.ui.Modifier
import edu.unicauca.aplimovil.masccotas_v1.Direccion.AgregarDireccionScreen
import edu.unicauca.aplimovil.masccotas_v1.Home.HomeScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(navController)
        }
        composable("register") {
            val registerViewModel: RegisterViewModel = viewModel()
            RegisterScreen(
                navController = navController,
                modifier = Modifier,
                viewModel = registerViewModel
            )
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("agregar_direccion") {
            AgregarDireccionScreen(navController)
        }
    }
}