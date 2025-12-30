package com.project.finalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ComparisonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comparison)

        val rawData = intent.getSerializableExtra("DATA_COMPARE") as? ArrayList<Smartwatch> ?: ArrayList()


        SpkCalculator.calculateSAW(rawData)

        val rvComparison = findViewById<RecyclerView>(R.id.rv_comparison)

        rvComparison.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        rvComparison.adapter = ComparisonAdapter(rawData)
    }
}
