package com.example.stocksapp.api.responsedto.graph.monthly

import com.example.stocksapp.api.responsedto.graph.StockGraphDataItem
import com.google.gson.annotations.SerializedName

data class MonthlyTimeSeriesResponse (
    @SerializedName("Meta Data" )
    var metaData   : MonthlyTimeSeriesMetaData,
    @SerializedName("Monthly Time Series" )
    val timeSeriesMonthly: Map<String, StockGraphDataItem>
)

