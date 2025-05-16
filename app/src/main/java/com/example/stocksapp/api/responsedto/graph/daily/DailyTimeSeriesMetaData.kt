package com.example.stocksapp.api.responsedto.graph.daily

import com.google.gson.annotations.SerializedName


data class DailyTimeSeriesMetaData(
    @SerializedName("1. Information")
    val information: String,

    @SerializedName("2. Symbol")
    val symbol: String,

    @SerializedName("3. Last Refreshed")
    val lastRefreshed: String,

    @SerializedName("4. Output Size")
    val outputSize: String,

    @SerializedName("5. Time Zone")
    val timeZone: String
)