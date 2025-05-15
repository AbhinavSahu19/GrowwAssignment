package com.example.growwassignment.responsedto.graph.monthly

import com.example.growwassignment.responsedto.graph.StockGraphDataItem
import com.google.gson.annotations.SerializedName

data class MonthlyTimeSeriesResponse (
    @SerializedName("Meta Data" )
    var metaData   : MonthlyTimeSeriesMetaData,
    @SerializedName("Monthly Time Series" )
    val timeSeriesWeekly: Map<String, StockGraphDataItem>
)

