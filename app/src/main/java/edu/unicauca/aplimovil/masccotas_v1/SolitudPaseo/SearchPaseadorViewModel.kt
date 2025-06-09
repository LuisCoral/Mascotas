package edu.unicauca.aplimovil.masccotas_v1.SolitudPaseo

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.Timestamp

class SearchPaseadorViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    fun enviarSolicitudPaseo(
        direccion: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val solicitud = hashMapOf(
            "direccion" to direccion,
            "usuarioAnonimo" to true, // Puedes cambiar esto por un dato que quieras
            "timestamp" to Timestamp.now(),
            "estado" to "pendiente"
        )
        db.collection("solicitudes_paseo")
            .add(solicitud)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> onError(e.message ?: "Error desconocido") }
    }
}