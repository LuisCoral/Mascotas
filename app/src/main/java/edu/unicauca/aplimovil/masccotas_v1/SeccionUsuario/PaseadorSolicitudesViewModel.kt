package edu.unicauca.aplimovil.masccotas_v1.SeccionUsuario


// --- ViewModel para solicitudes ---
import androidx.lifecycle.ViewModel

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow



//data class SolicitudPaseo(
//    val id: String = "",
//    val direccion: String = "",
//    val estado: String = ""
//)
//
//class PaseadorSolicitudesViewModel : ViewModel() {
//    private val db = FirebaseFirestore.getInstance()
//    private val _solicitudes = MutableStateFlow<List<SolicitudPaseo>>(emptyList())
//    val solicitudes: StateFlow<List<SolicitudPaseo>> = _solicitudes
//
//    private val _mensajePaseador = MutableStateFlow<String?>(null)
//    val mensajePaseador: StateFlow<String?> = _mensajePaseador
//
//    init {
//        cargarSolicitudes()
//    }
//
//    fun cargarSolicitudes() {
//        db.collection("solicitudes_paseo")
//            .whereEqualTo("estado", "pendiente")
//            .addSnapshotListener { result, _ ->
//                val lista = result?.documents?.map { doc ->
//                    SolicitudPaseo(
//                        id = doc.id,
//                        direccion = doc.getString("direccion") ?: "",
//                        estado = doc.getString("estado") ?: ""
//                    )
//                } ?: emptyList()
//                _solicitudes.value = lista
//            }
//    }
//
//    fun aceptarSolicitud(solicitud: SolicitudPaseo) {
//        db.collection("solicitudes_paseo").document(solicitud.id)
//            .update("estado", "aceptada")
//            .addOnSuccessListener {
//                _mensajePaseador.value = "¡El paseo comienza ahora!"
//            }
//    }
//
//    fun limpiarMensaje() {
//        _mensajePaseador.value = null
//    }
//}

data class SolicitudPaseo(
    val id: String = "",
    val direccion: String = "",
    val precio: String = "",
    val estado: String = ""
)

class PaseadorSolicitudesViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _solicitudes = MutableStateFlow<List<SolicitudPaseo>>(emptyList())
    val solicitudes: StateFlow<List<SolicitudPaseo>> = _solicitudes

    private val _mensajePaseador = MutableStateFlow<String?>(null)
    val mensajePaseador: StateFlow<String?> = _mensajePaseador

    init {
        cargarSolicitudes()
    }

    fun cargarSolicitudes() {
        db.collection("solicitudes_paseo")
            .whereEqualTo("estado", "pendiente")
            .addSnapshotListener { result, _ ->
                val lista = result?.documents?.map { doc ->
                    SolicitudPaseo(
                        id = doc.id,
                        direccion = doc.getString("direccion") ?: "",
                        precio = doc.getString("precio") ?: "",
                        estado = doc.getString("estado") ?: ""
                    )
                } ?: emptyList()
                _solicitudes.value = lista
            }
    }

    fun aceptarSolicitud(solicitud: SolicitudPaseo) {
        db.collection("solicitudes_paseo").document(solicitud.id)
            .update("estado", "aceptada")
            .addOnSuccessListener {
                _mensajePaseador.value = "¡El paseo comienza ahora!"
            }
    }

    fun limpiarMensaje() {
        _mensajePaseador.value = null
    }
}