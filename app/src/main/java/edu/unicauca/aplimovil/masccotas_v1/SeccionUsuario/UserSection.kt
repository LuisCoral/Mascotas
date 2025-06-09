package edu.unicauca.aplimovil.masccotas_v1.SeccionUsuario


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun UserSection() {
    var modoPaseador by remember { mutableStateOf(false) }
    if (modoPaseador) {
        PaseadorSolicitudesSection(onSalir = { modoPaseador = false })
    } else {
        UserInfoSection(onModoPaseador = { modoPaseador = true })
    }
}

@Composable
fun UserInfoSection(onModoPaseador: () -> Unit) {
    var showEditDialog by remember { mutableStateOf(false) }
    // Simulación de datos de usuario
    val nombre = "Usuario de Ejemplo"
    val correo = "usuario@correo.com"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Información del Usuario", style = MaterialTheme.typography.h6, color = MaterialTheme.colors.primary)
                Spacer(modifier = Modifier.height(12.dp))
                Text("Nombre: $nombre")
                Text("Correo: $correo")
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { showEditDialog = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Editar Información")
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onModoPaseador,
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text("Modo Paseador", style = MaterialTheme.typography.h6, color = Color.White)
        }
    }

    if (showEditDialog) {
        EditUserDialog(onDismiss = { showEditDialog = false })
    }
}

@Composable
fun EditUserDialog(onDismiss: () -> Unit) {
    var nombre by remember { mutableStateOf("Usuario de Ejemplo") }
    var correo by remember { mutableStateOf("usuario@correo.com") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar información") },
        text = {
            Column {
                OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
                OutlinedTextField(value = correo, onValueChange = { correo = it }, label = { Text("Correo") })
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) { Text("Guardar") }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}

@Composable
fun PaseadorSolicitudesSection(onSalir: () -> Unit, viewModel: PaseadorSolicitudesViewModel = viewModel()) {
    val solicitudes by viewModel.solicitudes.collectAsState()
    val cargando by viewModel.cargando.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Solicitudes Pendientes", style = MaterialTheme.typography.h6, color = MaterialTheme.colors.secondary)
        Spacer(modifier = Modifier.height(16.dp))
        if (cargando) {
            CircularProgressIndicator()
        } else {
            if (solicitudes.isEmpty()) {
                Text("No hay solicitudes pendientes en este momento.")
            } else {
                solicitudes.forEach { solicitud ->
                    Card(
                        elevation = 6.dp,
                        shape = RoundedCornerShape(16.dp),
                        backgroundColor = Color(0xFFFAFAFA),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Dirección: ${solicitud.direccion}")
                            Text("Solicitante: ${solicitud.usuarioId ?: "Anónimo"}")
                            Button(
                                onClick = { viewModel.aceptarSolicitud(solicitud) },
                                modifier = Modifier.padding(top = 8.dp)
                            ) {
                                Text("Aceptar solicitud")
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedButton(
            onClick = onSalir,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salir de Modo Paseador")
        }
    }
}
