package com.timlam.screencolorsmvi.firebase

sealed class FirestoreResponse<out T> {

    class Loading<out T> : FirestoreResponse<T>()

    data class Success<out T>(val data: T) : FirestoreResponse<T>()

    data class Failure<out T>(val exception: Exception) : FirestoreResponse<T>()

}

