package edu.unicauca.aplimovil.masccotas_v1.SolitudPaseo


import edu.unicauca.aplimovil.masccotas_v1.R

import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SearchSection(
    searchPaseadorViewModel: SearchPaseadorViewModel = viewModel()
) {
    var direccion by remember { mutableStateOf("") }
    var enviando by remember { mutableStateOf(false) }
    var solicitudEnviada by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf<String?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Buscar Paseador",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Imagen del mapa dentro de un cuadro dinámico y amigable
            Card(
                elevation = 8.dp,
                shape = RoundedCornerShape(24.dp),
                backgroundColor = Color(0xFFE3F2FD), // Azul claro amigable
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.map),
                        contentDescription = "Mapa simulado",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = direccion,
                onValueChange = { direccion = it },
                label = { Text("Dirección o zona") },
                enabled = !enviando && !solicitudEnviada,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    enviando = true
                    errorMsg = null
                    searchPaseadorViewModel.enviarSolicitudPaseo(
                        direccion = direccion,
                        onSuccess = {
                            solicitudEnviada = true
                            enviando = false
                        },
                        onError = { error ->
                            errorMsg = error
                            enviando = false
                        }
                    )
                },
                enabled = direccion.isNotBlank() && !enviando && !solicitudEnviada,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Buscar Paseador")
            }

            if (enviando) {
                Spacer(modifier = Modifier.height(24.dp))
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(8.dp))
                Text("Enviando solicitud…")
            }

            if (solicitudEnviada) {
                Spacer(modifier = Modifier.height(24.dp))
                Card(
                    elevation = 8.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Solicitud enviada!",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Espera a que un paseador acepte tu solicitud. Te notificaremos aquí mismo.",
                            style = MaterialTheme.typography.body2
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                direccion = ""
                                solicitudEnviada = false
                            }
                        ) {
                            Text("Nueva búsqueda")
                        }
                    }
                }
            }

            errorMsg?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(it, color = MaterialTheme.colors.error)
            }
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}