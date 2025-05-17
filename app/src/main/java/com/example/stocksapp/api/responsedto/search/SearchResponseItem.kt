package com.example.stocksapp.api.responsedto.search

import com.example.stocksapp.db.entity.SearchKeywordEntity
import com.google.gson.annotations.SerializedName

data class SearchResponseItem(

    @SerializedName("1. symbol")
    var symbol: String,
    @SerializedName("2. name")
    var name: String,
    @SerializedName("3. type")
    var type: String,
    @SerializedName("4. region")
    var region: String,
    @SerializedName("5. marketOpen")
    var marketOpen: String,
    @SerializedName("6. marketClose")
    var marketClose: String,
    @SerializedName("7. timezone")
    var timezone: String,
    @SerializedName("8. currency")
    var currency: String,
    @SerializedName("9. matchScore")
    var matchScore: String

) {
    fun toEntity(): SearchKeywordEntity {
        return SearchKeywordEntity(
            symbol,
            name,
            type,
            region,
            marketOpen,
            marketClose,
            timezone,
            currency,
            matchScore
        )
    }
}