package edu.unicauca.aplimovil.masccotas_v1.SeccionUsuario


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


//@Composable
//fun UserSection() {
//    var modoPaseador by remember { mutableStateOf(false) }
//    if (modoPaseador) {
//        PaseadorSolicitudesSection(onSalir = { modoPaseador = false })
//    } else {
//        UserInfoSection(onModoPaseador = { modoPaseador = true })
//    }
//}
//
//@Composable
//fun UserInfoSection(onModoPaseador: () -> Unit) {
//    var showEditDialog by remember { mutableStateOf(false) }
//    // Simulación de datos de usuario
//    val nombre = "Usuario de Ejemplo"
//    val correo = "usuario@correo.com"
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Card(
//            elevation = 8.dp,
//            shape = RoundedCornerShape(20.dp),
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier.padding(24.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text("Información del Usuario", style = MaterialTheme.typography.h6, color = MaterialTheme.colors.primary)
//                Spacer(modifier = Modifier.height(12.dp))
//                Text("Nombre: $nombre")
//                Text("Correo: $correo")
//                Spacer(modifier = Modifier.height(20.dp))
//                Button(
//                    onClick = { showEditDialog = true },
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Text("Editar Información")
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        Button(
//            onClick = onModoPaseador,
//            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(60.dp)
//        ) {
//            Text("Modo Paseador", style = MaterialTheme.typography.h6, color = Color.White)
//        }
//    }
//
//    if (showEditDialog) {
//        EditUserDialog(onDismiss = { showEditDialog = false })
//    }
//}
//
//@Composable
//fun EditUserDialog(onDismiss: () -> Unit) {
//    var nombre by remember { mutableStateOf("Usuario de Ejemplo") }
//    var correo by remember { mutableStateOf("usuario@correo.com") }
//    AlertDialog(
//        onDismissRequest = onDismiss,
//        title = { Text("Editar información") },
//        text = {
//            Column {
//                OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
//                OutlinedTextField(value = correo, onValueChange = { correo = it }, label = { Text("Correo") })
//            }
//        },
//        confirmButton = {
//            Button(onClick = onDismiss) { Text("Guardar") }
//        },
//        dismissButton = {
//            OutlinedButton(onClick = onDismiss) { Text("Cancelar") }
//        }
//    )
//}


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
        // Icono de usuario centrado arriba
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Usuario",
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape),
            tint = Color(0xFF90CAF9)
        )

        Spacer(modifier = Modifier.height(24.dp))

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


//Modo Paeador

@Composable
fun PaseadorSolicitudesSection(
    onSalir: () -> Unit,
    viewModel: PaseadorSolicitudesViewModel = viewModel()
) {
    val solicitudes by viewModel.solicitudes.collectAsState()
    val mensajePaseador by viewModel.mensajePaseador.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título centrado
            Text(
                "Solicitudes Pendientes",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.secondary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )

            // Card centrada con las solicitudes
            Card(
                elevation = 10.dp,
                shape = RoundedCornerShape(28.dp),
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .wrapContentHeight()
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (solicitudes.isEmpty()) {
                        Text(
                            "No hay solicitudes pendientes.",
                            textAlign = TextAlign.Center
                        )
                    } else {
                        solicitudes.forEach { solicitud ->
                            Card(
                                elevation = 3.dp,
                                shape = RoundedCornerShape(16.dp),
                                backgroundColor = MaterialTheme.colors.surface,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        "Dirección: ${solicitud.direccion}",
                                        style = MaterialTheme.typography.subtitle1,
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        "Precio ofrecido: ${solicitud.precio}",
                                        style = MaterialTheme.typography.body2,
                                        color = MaterialTheme.colors.primaryVariant,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                                    )
                                    Button(
                                        onClick = { viewModel.aceptarSolicitud(solicitud) },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Aceptar solicitud")
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedButton(
                onClick = onSalir,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(48.dp)
            ) {
                Text("Salir de Modo Paseador", style = MaterialTheme.typography.button)
            }
        }

        // Mensaje al paseador
        mensajePaseador?.let {
            AlertDialog(
                onDismissRequest = { viewModel.limpiarMensaje() },
                title = { Text("Confirmación") },
                text = { Text(it) },
                confirmButton = {
                    Button(onClick = { viewModel.limpiarMensaje() }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}
