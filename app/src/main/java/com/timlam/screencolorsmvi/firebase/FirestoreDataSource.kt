package com.timlam.screencolorsmvi.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
class FirestoreDataSource {

    companion object {

        private const val COLORS_COLLECTION = "firebaseColors"
        private const val COLOR_DOCUMENT = "color"

    }

    private val database: FirebaseFirestore = Firebase.firestore
    private val colorDocument = database.collection(COLORS_COLLECTION).document(COLOR_DOCUMENT)

    suspend fun getColor(): Flow<FirestoreResponse<FirestoreColor>> = callbackFlow {
        val subscription = colorDocument.addSnapshotListener { value, error ->
            if (error != null) offer(FirestoreResponse.Failure(error))

            if (value != null && value.exists()) {
                val color = value.toObject(FirestoreColor::class.java)
                if (color != null) offer(FirestoreResponse.Success(color))
            }
        }

        awaitClose { subscription.remove() }
    }

}
