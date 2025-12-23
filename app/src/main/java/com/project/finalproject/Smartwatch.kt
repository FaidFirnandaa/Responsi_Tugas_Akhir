package com.project.finalproject

import com.google.firebase.database.Exclude
import java.io.Serializable

data class Smartwatch(
    val id: String = "",
    val nama: String = "",
    val baterai: Int = 0,
    val bluetooth: Double = 0.0,
    val layar: String = "",
    val layarScore: Int = 0,
    val ipRating: String = "",
    val ipScore: Int = 0,
    val gps: Boolean = false,
    val harga: Long = 0,
    val imageUrl: String = "" // Menggunakan nama field imageUrl untuk konsistensi dengan Adapter
) : Serializable {

    @Exclude
    var isSelected: Boolean = false // Helper untuk checkbox di UI

    @Exclude
    var spkScore: Double = 0.0
}