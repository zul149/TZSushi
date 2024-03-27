package com.example.tzsushi.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val carbohydrates_per_100_grams: Double,
    val category_id: Int,
    val description: String,
    val energy_per_100_grams: Double,
    val fats_per_100_grams: Double,
    val id: Int,
    val image: String,
    var measure: Int,
    val measure_unit: String,
    val name: String,
    val price_current: Int,
    val price_old: Int,
    val proteins_per_100_grams: Double,
    val tag_ids: List<Int>
): Parcelable