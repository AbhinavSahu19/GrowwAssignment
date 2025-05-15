package com.example.stocksapp.api

import com.example.stocksapp.responsedto.CompanyOverview
import com.example.stocksapp.responsedto.TopGainersLosersResponse
import com.example.stocksapp.responsedto.graph.daily.DailyTimeSeriesResponse
import com.example.stocksapp.responsedto.graph.monthly.MonthlyTimeSeriesResponse
import com.example.stocksapp.responsedto.graph.weekly.WeeklyTimeSeriesResponse
import com.example.stocksapp.responsedto.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/query")
    suspend fun getTopGainersLosers(
        @Query("function") function: String = "TOP_GAINERS_LOSERS"
    ): TopGainersLosersResponse

    @GET("/query")
    suspend fun getSearchKeywords(
        @Query("function") function: String = "SYMBOL_SEARCH",
        @Query("keywords") keyword: String
    ): SearchResponse

    @GET("/query")
    suspend fun getCompanyOverview(
        @Query("function") function: String = "OVERVIEW",
        @Query("symbol") symbol: String
    ): CompanyOverview

    @GET("/query")
    suspend fun getDailyTimeSeries(
        @Query("function") function: String = "TIME_SERIES_DAILY",
        @Query("symbol") symbol: String
    ): DailyTimeSeriesResponse

    @GET("/query")
    suspend fun getWeeklyTimeSeries(
        @Query("function") function: String = "TIME_SERIES_WEEKLY",
        @Query("symbol") symbol: String
    ): WeeklyTimeSeriesResponse

    @GET("/query")
    suspend fun getMonthlyTimeSeries(
        @Query("function") function: String = "TIME_SERIES_MONTHLY",
        @Query("symbol") symbol: String
    ): MonthlyTimeSeriesResponse
}