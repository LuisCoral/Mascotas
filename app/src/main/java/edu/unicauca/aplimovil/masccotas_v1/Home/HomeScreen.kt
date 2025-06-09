package edu.unicauca.aplimovil.masccotas_v1.Home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.unicauca.aplimovil.masccotas_v1.SeccionUsuario.UserSection
import edu.unicauca.aplimovil.masccotas_v1.SolitudPaseo.SearchSection

@Composable
fun HomeScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }
    val items = listOf("Home", "Buscar", "Usuario")
    val icons = listOf(Icons.Default.Home, Icons.Default.Search, Icons.Default.Person)

    Scaffold(
        floatingActionButton = {
            if (selectedIndex == 0) {
                FloatingActionButton(
                    onClick = { navController.navigate("agregar_direccion") },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(Icons.Default.LocationOn, contentDescription = "Agregar Dirección")
                }
            }
        },
        bottomBar = {
            BottomNavigation {
                items.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = { Icon(icons[index], contentDescription = item) },
                        label = { Text(item) },
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedIndex) {
                0 -> RecommendationsSection(navController)
                1 -> SearchSection()
                2 -> UserSection()
            }
        }
    }
}

@Composable
fun RecommendationsSection(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Recomendaciones para tu mascota",
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(3) { idx ->
                val (title, desc) = when (idx) {
                    0 -> "Ejercicio diario" to "Saca a pasear a tu mascota todos los días para que mantenga una buena salud física y mental."
                    1 -> "Visitas al veterinario" to "No olvides realizar chequeos periódicos para prevenir enfermedades."
                    else -> "Alimentación adecuada" to "Asegúrate de darle alimento balanceado y agua fresca todos los días."
                }
                Card(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(12.dp),
                    backgroundColor = Color(0xFFFAFAFA),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(title, style = MaterialTheme.typography.subtitle1, color = MaterialTheme.colors.secondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(desc)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Vínculo alternativo para agregar dirección
        Text(
            text = "¿Deseas registrar una dirección?",
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .clickable { navController.navigate("agregar_direccion") }
                .padding(8.dp)
        )
    }
}

//@Composable
//fun SearchSection() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text("Pantalla de búsqueda (¡personalízala como prefieras!)")
//    }
//}

//@Composable
//fun UserSection() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text("Información del usuario (aquí puedes mostrar datos del perfil)")
//    }
//}