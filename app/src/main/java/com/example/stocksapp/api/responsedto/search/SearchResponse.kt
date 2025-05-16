package com.example.stocksapp.api.responsedto.search

import com.google.gson.annotations.SerializedName

data class SearchResponse (

    @SerializedName("bestMatches" )
    var bestMatches : List<SearchResponseItem> = listOf()

)