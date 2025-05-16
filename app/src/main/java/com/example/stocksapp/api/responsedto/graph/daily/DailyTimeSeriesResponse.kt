package com.example.stocksapp.api.responsedto.graph.daily

import com.example.stocksapp.api.responsedto.graph.StockGraphDataItem
import com.google.gson.annotations.SerializedName

data class DailyTimeSeriesResponse (
    @SerializedName("Meta Data")
    val metaData: DailyTimeSeriesMetaData,

    @SerializedName("Time Series (Daily)")
    val timeSeriesDaily: Map<String, StockGraphDataItem>
)
