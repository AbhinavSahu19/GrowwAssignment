package com.example.stocksapp.api.responsedto.graph.monthly

import com.google.gson.annotations.SerializedName

data class MonthlyTimeSeriesMetaData(
    @SerializedName("1. Information")
    val information: String,

    @SerializedName("2. Symbol")
    val symbol: String,

    @SerializedName("3. Last Refreshed")
    val lastRefreshed: String,

    @SerializedName("5. Time Zone")
    val timeZone: String
)