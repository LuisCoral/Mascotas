package edu.unicauca.aplimovil.masccotas_v1.SeccionUsuario


// --- ViewModel para solicitudes ---
import androidx.lifecycle.ViewModel

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


data class SolicitudPaseo(
    val id: String = "",
    val direccion: String = "",
    val usuarioId: String? = null,
    val estado: String = ""
)

class PaseadorSolicitudesViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _solicitudes = MutableStateFlow<List<SolicitudPaseo>>(emptyList())
    val solicitudes: StateFlow<List<SolicitudPaseo>> = _solicitudes
    private val _cargando = MutableStateFlow(true)
    val cargando: StateFlow<Boolean> = _cargando

    init {
        cargarSolicitudes()
    }

    fun cargarSolicitudes() {
        _cargando.value = true
        db.collection("solicitudes_paseo")
            .whereEqualTo("estado", "pendiente")
            .get()
            .addOnSuccessListener { result ->
                val lista = result.documents.map { doc ->
                    SolicitudPaseo(
                        id = doc.id,
                        direccion = doc.getString("direccion") ?: "",
                        usuarioId = doc.getString("usuarioId"),
                        estado = doc.getString("estado") ?: ""
                    )
                }
                _solicitudes.value = lista
                _cargando.value = false
            }
            .addOnFailureListener {
                _solicitudes.value = emptyList()
                _cargando.value = false
            }
    }

    fun aceptarSolicitud(solicitud: SolicitudPaseo) {
        db.collection("solicitudes_paseo").document(solicitud.id)
            .update("estado", "aceptada")
            .addOnSuccessListener {
                cargarSolicitudes()
            }
    }
}