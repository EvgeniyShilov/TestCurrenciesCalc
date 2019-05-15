package com.example.testcurrenciescalc.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Rate(
    @SerializedName("currency_code")
    @PrimaryKey
    val currencyCode: String,
    @SerializedName("unit_value")
    val unitValue: Long,
    @SerializedName("median_rate")
    val medianRate: Double,
    @SerializedName("buying_rate")
    val buyingRate: Double,
    @SerializedName("selling_rate")
    val sellingRate: Double
)
