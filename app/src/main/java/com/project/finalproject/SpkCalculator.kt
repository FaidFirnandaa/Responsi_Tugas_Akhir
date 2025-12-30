package com.project.finalproject

object SpkCalculator {

    /**
     * Menghitung nilai SPK menggunakan metode SAW (Simple Additive Weighting).
     * Kriteria:
     * 1. Baterai (Benefit)
     * 2. IP Score (Benefit)
     * 3. GPS (Benefit)
     * 4. Harga (Cost)
     */
    fun calculateSAW(list: List<Smartwatch>): List<Smartwatch> {
        if (list.isEmpty()) return list


        val wBaterai = 0.25
        val wIpScore = 0.25
        val wGps = 0.20
        val wHarga = 0.30

        val maxBaterai = list.maxOf { it.baterai }.toDouble()
        val maxIpScore = list.maxOf { it.ipScore }.toDouble()

        val maxGpsVal = list.maxOf { if (it.gps) 1.0 else 0.0 }

        val minHarga = list.minOf { it.harga }.toDouble()

        for (item in list) {

            val rBaterai = if (maxBaterai > 0) item.baterai / maxBaterai else 0.0

            val rIpScore = if (maxIpScore > 0) item.ipScore / maxIpScore else 0.0

            val valGps = if (item.gps) 1.0 else 0.0
            val rGps = if (maxGpsVal > 0) valGps / maxGpsVal else 0.0

            val rHarga = if (item.harga > 0) minHarga / item.harga else 0.0

            val score = (wBaterai * rBaterai) +
                        (wIpScore * rIpScore) +
                        (wGps * rGps) +
                        (wHarga * rHarga)

            item.spkScore = score
        }

        return list
    }
}
