package com.project.finalproject

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ComparisonAdapter(
    private val list: List<Smartwatch>
) : RecyclerView.Adapter<ComparisonAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Inisialisasi komponen UI dari item_comparison_column.xml
        val imgProduct: ImageView = view.findViewById(R.id.iv_comp_image)
        val tvName: TextView = view.findViewById(R.id.tv_comp_name)
        val tvBattery: TextView = view.findViewById(R.id.tv_comp_battery)
        val tvBluetooth: TextView = view.findViewById(R.id.tv_comp_bluetooth)
        val tvScreen: TextView = view.findViewById(R.id.tv_comp_screen)
        val tvPrice: TextView = view.findViewById(R.id.tv_comp_price)
        val tvScore: TextView = view.findViewById(R.id.tv_comp_score)

        fun bind(item: Smartwatch) {
            // 1. Set Nama
            tvName.text = item.nama

            // 2. Set Gambar menggunakan Glide
            if (item.imageUrl.isNotEmpty()) {
                Glide.with(itemView.context)
                    .load(item.imageUrl)
                    .into(imgProduct)
            } else {
                // Gambar default jika URL kosong
                imgProduct.setImageResource(R.drawable.ic_launcher_background)
            }

            // 3. Set Spesifikasi dengan format teks tambahan
            tvBattery.text = "${item.baterai} mAh"
            tvBluetooth.text = "v${item.bluetooth}"
            tvScreen.text = item.layar

            // Format Rupiah sederhana
            val formattedPrice = String.format("%,d", item.harga).replace(',', '.')
            tvPrice.text = "Rp $formattedPrice"

            // 4. Set Nilai Hasil SPK
            // Kita batasi 2 angka di belakang koma agar rapi
            val formattedScore = String.format("%.2f", item.spkScore)
            tvScore.text = formattedScore

            // Opsional: Beri warna merah/hijau jika nilainya tinggi (Visual Cue)
            // Asumsi: Jika score > 0.8 maka warnanya Hijau (Rekomendasi)
            if (item.spkScore > 0.8) {
                tvScore.setTextColor(Color.parseColor("#4CAF50")) // Hijau
            } else {
                tvScore.setTextColor(Color.BLACK)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Menghubungkan dengan layout XML kolom perbandingan
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comparison_column, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}