package edu.unicauca.aplimovil.masccotas_v1.Registro

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel


//Original
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel
) {
    // Estados para los campos del formulario
    val nombre = remember { mutableStateOf("") }
    val correo = remember { mutableStateOf("") }
    val celular = remember { mutableStateOf("") }
    val cedula = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    // SnackbarHostState para mostrar mensajes
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // ViewModel
    //val viewModel: RegisterViewModel = viewModel()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
//            // Botón de regreso
//            bottonRedondoStateless(
//                onClick = { navController.popBackStack() },
//                icon = Icons.Default.ArrowBack,
//                colors = MaterialTheme.colorScheme.tertiary,
//                modifier = Modifier
//                    .align(Alignment.TopStart)
//                    .padding(16.dp)
//                    .size(width = 40.dp, height = 40.dp)
//            )

            // Formulario de registro
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            ) {
//                Spacer(modifier = Modifier.height(16.dp))
//                Box(modifier = Modifier.align(Alignment.CenterHorizontally)) { Imagenes(R.drawable.logo, 100) }
//                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = nombre.value,
                    onValueChange = { nombre.value = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = correo.value,
                    onValueChange = { correo.value = it },
                    label = { Text("Correo") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = celular.value,
                    onValueChange = { celular.value = it },
                    label = { Text("Celular") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = cedula.value,
                    onValueChange = { cedula.value = it },
                    label = { Text("Cédula") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (
                            nombre.value.isBlank() ||
                            correo.value.isBlank() ||
                            celular.value.isBlank() ||
                            cedula.value.isBlank() ||
                            password.value.isBlank()
                        ) {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Todos los campos son obligatorios",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        } else {
                            val usuario = Usuario(
                                nombre = nombre.value,
                                correo = correo.value,
                                celular = celular.value,
                                cedula = cedula.value,
                                password = password.value
                            )
                            // Llamar al ViewModel para registrar el usuario
                            viewModel.registrarUsuario(
                                usuario = usuario,
                                onSuccess = {
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = "✅ Usuario registrado correctamente",
                                            duration = SnackbarDuration.Short
                                        )
                                        delay(1000)
                                        navController.navigate("pantalla_destino") // Cambia "pantalla_destino" por tu pantalla de destino
                                    }
                                },
                                onError = { error ->
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = error,
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                }
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Registrar")
                }
            }
        }
    }
}