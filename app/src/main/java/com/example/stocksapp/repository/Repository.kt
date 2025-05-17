package com.example.stocksapp.repository

import com.example.stocksapp.utils.ResponseModel
import com.example.stocksapp.api.responsedto.graph.StockGraphDataItem
import com.example.stocksapp.api.responsedto.search.SearchResponseItem
import com.example.stocksapp.db.entity.ActivelyTradedEntity
import com.example.stocksapp.db.entity.SearchKeywordEntity
import com.example.stocksapp.db.entity.TopGainerEntity
import com.example.stocksapp.db.entity.TopLooserEntity
import com.example.stocksapp.presentation.details.CompanyOverviewScreenData
import com.example.stocksapp.presentation.explore.ExploreScreenData
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getRecentKeywords(): Flow<List<SearchKeywordEntity>>

    fun getExploreScreenData(): Flow<ResponseModel<ExploreScreenData>>

    fun getTopGainers(): Flow<ResponseModel<List<TopGainerEntity>>>

    fun getTopLooser(): Flow<ResponseModel<List<TopLooserEntity>>>

    fun getActivelyTraded(): Flow<ResponseModel<List<ActivelyTradedEntity>>>

    fun getSearchKeywords(keyword: String): Flow<ResponseModel<List<SearchResponseItem>>>

    fun getCompanyOverview(symbol: String): Flow<ResponseModel<CompanyOverviewScreenData>>

    fun reloadCompanyOverview(symbol: String): Flow<ResponseModel<CompanyOverviewScreenData>>

    fun getCompanyDailyGraph(symbol: String): Flow<ResponseModel<Map<String, StockGraphDataItem>>>

    fun getCompanyWeeklyGraph(symbol: String): Flow<ResponseModel<Map<String, StockGraphDataItem>>>

    fun getCompanyMonthlyGraph(symbol: String): Flow<ResponseModel<Map<String, StockGraphDataItem>>>

    suspend fun addRecentSearch(keyword: SearchResponseItem)
}