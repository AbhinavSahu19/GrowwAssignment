package com.example.stocksapp.presentation.explore

import com.example.stocksapp.db.entity.ActivelyTradedEntity
import com.example.stocksapp.db.entity.TopGainerEntity
import com.example.stocksapp.db.entity.TopLooserEntity

data class ExploreScreenData(
    val topGainers: List<TopGainerEntity>,
    val topLosers: List<TopLooserEntity>,
    val activeTraded: List<ActivelyTradedEntity>
)
