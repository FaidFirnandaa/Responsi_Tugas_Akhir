package com.project.finalproject

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: SmartwatchAdapter
    private val smartwatchList = ArrayList<Smartwatch>() // Menyimpan data asli

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Setup RecyclerView
        val rv = findViewById<RecyclerView>(R.id.rv_smartwatch)
        rv.layoutManager = LinearLayoutManager(this)

        adapter = SmartwatchAdapter(ArrayList()) {
            // Callback onClick jika dibutuhkan nanti
        }
        rv.adapter = adapter

        // 2. Setup Search Listener
        val etSearch = findViewById<EditText>(R.id.et_search)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterData(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // 3. Load Data Statis
        loadStaticData()

        // 4. Tombol Bandingkan
        findViewById<Button>(R.id.btn_compare).setOnClickListener {
            val selected = adapter.selectedItems
            if (selected.size < 2) {
                Toast.makeText(this, "Pilih minimal 2 smartwatch", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ComparisonActivity::class.java)
                intent.putExtra("DATA_COMPARE", selected)
                startActivity(intent)
            }
        }
    }

    private fun loadStaticData() {
        smartwatchList.clear()
        smartwatchList.addAll(listOf(
            Smartwatch("smarthwatch_001", "Galaxy Watch 8 Classic", 445, 5.3, "super AMOLED", 5, "IP68", 3, true, 6999000, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_001.jpg"),
            Smartwatch("smarthwatch_002", "Galaxy Watch 8", 435, 5.3, "super AMOLED", 5, "IP68", 3, true, 5490000, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_002.jpg"),
            Smartwatch("smarthwatch_003", "Galaxy Watch 7", 425, 5.3, "super AMOLED", 5, "IP68", 3, true, 4999000, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_003.jpg"),
            Smartwatch("smarthwatch_004", "Galaxy Watch FE", 247, 5.0, "super AMOLED", 5, "IP68", 3, true, 2599000, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_004.jpg"),
            Smartwatch("smarthwatch_005", "Galaxy Watch 6 Classic", 425, 5.3, "super AMOLED", 5, "IP68", 3, true, 3299000, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_005.jpg"),
            Smartwatch("smarthwatch_006", "Galaxy Watch 6", 425, 5.3, "super AMOLED", 5, "IP68", 3, true, 2499000, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_006.jpg"),
            Smartwatch("smarthwatch_007", "Huawei Watch D", 451, 5.1, "AMOLED", 4, "IP68", 3, true, 4699000, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_007.jpg"),
            Smartwatch("smarthwatch_008", "Huawei Watch Fit 4 Pro", 400, 5.2, "AMOLED", 4, "IP6X", 3, true, 2799000, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_008.jpg"),
            Smartwatch("smarthwatch_009", "Huawei Watch Fit 4", 400, 5.2, "AMOLED", 4, "IP6X", 3, true, 1999000, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_009.jpg"),
            Smartwatch("smarthwatch_010", "Huawei Watch D2", 524, 5.2, "AMOLED", 4, "IP68", 3, true, 4979000, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_010.jpg"),
            Smartwatch("smarthwatch_011", "Huawei Watch Fit mini", 180, 5.1, "AMOLED", 4, "5 ATM", 4, false, 600000, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_011.jpg"),
            Smartwatch("smarthwatch_012", "Xiaomi Smart Band 10", 233, 5.4, "AMOLED", 4, "5 ATM", 4, false, 699000, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_012.jpg"),
            Smartwatch("smarthwatch_013", "Xiaomi Redmi Watch 4", 470, 5.3, "AMOLED", 4, "5 ATM", 4, true, 749000, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_013.jpg"),
            Smartwatch("smarthwatch_014", "Xiaomi Redmi Watch 5", 550, 5.3, "AMOLED", 4, "IP68", 3, true, 1288000, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_014.jpg"),
            Smartwatch("smarthwatch_015", "Xiaomi Smart Band 9 Pro", 350, 5.4, "AMOLED", 4, "5 ATM", 4, true, 949000, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_015.jpg"),
            Smartwatch("smarthwatch_016", "Xiaomi Smart Band 9", 233, 5.4, "AMOLED", 4, "5 ATM", 4, false, 519000, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_016.jpg"),
            Smartwatch("smarthwatch_017", "Google Pixel Watch 4", 445, 6.0, "AMOLED", 4, "IP68", 3, true, 5700000, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_017.jpg"),
            Smartwatch("smarthwatch_018", "Google Pixel Watch 2", 306, 5.0, "AMOLED", 4, "IP68", 3, true, 5038662, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_018.jpg"),
            Smartwatch("smarthwatch_019", "Google Pixel Watch 1", 294, 5.0, "AMOLED", 4, "IP68", 3, true, 1600000, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_019.jpg"),
            Smartwatch("smarthwatch_020", "Google Pixel Watch 3", 420, 5.3, "AMOLED", 4, "IP68", 3, true, 6500000, "https://raw.githubusercontent.com/FaidFirnandaa/GambarPMOB/main/img_020.jpg")
        ))
        
        // Tampilkan data awal (sesuai search bar yang kosong)
        val etSearch = findViewById<EditText>(R.id.et_search)
        filterData(etSearch.text.toString())
    }

    private fun filterData(query: String) {
        val filteredList = ArrayList<Smartwatch>()
        if (query.isEmpty()) {
            filteredList.addAll(smartwatchList)
        } else {
            val lowerQuery = query.lowercase(Locale.getDefault())
            for (item in smartwatchList) {
                if (item.nama.lowercase(Locale.getDefault()).contains(lowerQuery)) {
                    filteredList.add(item)
                }
            }
        }
        adapter.updateList(filteredList)
    }
}