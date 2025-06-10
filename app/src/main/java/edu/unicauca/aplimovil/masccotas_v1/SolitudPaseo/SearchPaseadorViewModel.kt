package edu.unicauca.aplimovil.masccotas_v1.SolitudPaseo

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow



//class SearchPaseadorViewModel : ViewModel() {
//    private val db = FirebaseFirestore.getInstance()
//
//    private var solicitudListener: ListenerRegistration? = null
//
//    private val _solicitudAceptada = MutableStateFlow(false)
//    val solicitudAceptada = _solicitudAceptada.asStateFlow()
//    private val _solicitudId = MutableStateFlow<String?>(null)
//    val solicitudId = _solicitudId.asStateFlow()
//
//    fun enviarSolicitudPaseo(
//        direccion: String,
//        onSuccess: () -> Unit,
//        onError: (String) -> Unit
//    ) {
//        val solicitud = hashMapOf(
//            "direccion" to direccion,
//            "timestamp" to Timestamp.now(),
//            "estado" to "pendiente"
//        )
//        db.collection("solicitudes_paseo")
//            .add(solicitud)
//            .addOnSuccessListener { doc ->
//                _solicitudId.value = doc.id
//                escucharSolicitud(doc.id)
//                onSuccess()
//            }
//            .addOnFailureListener { e -> onError(e.message ?: "Error desconocido") }
//    }
//
//    fun escucharSolicitud(solicitudId: String) {
//        solicitudListener?.remove()
//        solicitudListener = db.collection("solicitudes_paseo").document(solicitudId)
//            .addSnapshotListener { docSnapshot, _ ->
//                if (docSnapshot != null && docSnapshot.exists()) {
//                    val estado = docSnapshot.getString("estado")
//                    if (estado == "aceptada") {
//                        _solicitudAceptada.value = true
//                    }
//                }
//            }
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        solicitudListener?.remove()
//    }
//}

class SearchPaseadorViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private var solicitudListener: ListenerRegistration? = null

    private val _solicitudAceptada = MutableStateFlow(false)
    val solicitudAceptada = _solicitudAceptada.asStateFlow()
    private val _solicitudId = MutableStateFlow<String?>(null)
    val solicitudId = _solicitudId.asStateFlow()

    fun enviarSolicitudPaseo(
        direccion: String,
        precio: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val solicitud = hashMapOf(
            "direccion" to direccion,
            "precio" to precio,
            "timestamp" to Timestamp.now(),
            "estado" to "pendiente"
        )
        db.collection("solicitudes_paseo")
            .add(solicitud)
            .addOnSuccessListener { doc ->
                _solicitudId.value = doc.id
                escucharSolicitud(doc.id)
                onSuccess()
            }
            .addOnFailureListener { e -> onError(e.message ?: "Error desconocido") }
    }

    fun escucharSolicitud(solicitudId: String) {
        solicitudListener?.remove()
        solicitudListener = db.collection("solicitudes_paseo").document(solicitudId)
            .addSnapshotListener { docSnapshot, _ ->
                if (docSnapshot != null && docSnapshot.exists()) {
                    val estado = docSnapshot.getString("estado")
                    if (estado == "aceptada") {
                        _solicitudAceptada.value = true
                    }
                }
            }
    }

    override fun onCleared() {
        super.onCleared()
        solicitudListener?.remove()
    }
}