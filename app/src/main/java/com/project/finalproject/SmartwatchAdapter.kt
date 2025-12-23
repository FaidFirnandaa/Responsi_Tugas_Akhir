package com.project.finalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SmartwatchAdapter(
    private var list: List<Smartwatch>,
    private val onSelect: (Smartwatch) -> Unit
) : RecyclerView.Adapter<SmartwatchAdapter.ViewHolder>() {

    // List untuk menyimpan item yang dicentang
    val selectedItems = ArrayList<Smartwatch>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivProduct: ImageView = view.findViewById(R.id.iv_product)
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvSpecs: TextView = view.findViewById(R.id.tv_specs)
        val tvPrice: TextView = view.findViewById(R.id.tv_price)
        val checkBox: CheckBox = view.findViewById(R.id.cb_select)

        fun bind(item: Smartwatch) {
            tvName.text = item.nama
            tvPrice.text = "Rp ${item.harga}"
            
            // Menampilkan spesifikasi singkat
            val specs = StringBuilder()
            if (item.layar.isNotEmpty()) specs.append(item.layar)
            if (item.baterai > 0) {
                if (specs.isNotEmpty()) specs.append(" | ")
                specs.append("${item.baterai} mAh")
            }
            if (item.ipRating.isNotEmpty()) {
                if (specs.isNotEmpty()) specs.append(" | ")
                specs.append(item.ipRating)
            }
            tvSpecs.text = specs.toString()

            Glide.with(itemView.context)
                .load(item.imageUrl)
                .placeholder(android.R.drawable.ic_menu_gallery) // Fallback image
                .error(android.R.drawable.ic_delete) // Error image
                .into(ivProduct)

            // Logika Checkbox
            checkBox.setOnCheckedChangeListener(null) // Reset listener
            checkBox.isChecked = selectedItems.contains(item)

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    if (!selectedItems.contains(item)) selectedItems.add(item)
                } else {
                    selectedItems.remove(item)
                }
            }
            
            // Klik item untuk toggle checkbox
            itemView.setOnClickListener {
                checkBox.isChecked = !checkBox.isChecked
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_smartwatch, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])
    override fun getItemCount() = list.size

    fun updateList(newList: List<Smartwatch>) {
        list = newList
        notifyDataSetChanged()
    }
}