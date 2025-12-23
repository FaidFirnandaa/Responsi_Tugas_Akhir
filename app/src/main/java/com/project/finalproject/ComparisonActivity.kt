package com.project.finalproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ComparisonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comparison) // Buat layout ini berisi RecyclerView Horizontal

        // 1. Ambil data dari Intent
        val rawData = intent.getSerializableExtra("DATA_COMPARE") as ArrayList<Smartwatch>

        // 2. (OPSIONAL) DISINI ANDA MASUKKAN RUMUS SPK
        // val rankedData = SpkCalculator.calculateSAW(rawData)
        // Untuk sekarang kita tampilkan rawData saja dulu

        // 3. Tampilkan Horizontal Scroll
        val rvComparison = findViewById<RecyclerView>(R.id.rv_comparison)

        // Layout Manager Horizontal agar bisa scroll ke samping jika > 2 item
        rvComparison.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        rvComparison.adapter = ComparisonAdapter(rawData)
    }
}