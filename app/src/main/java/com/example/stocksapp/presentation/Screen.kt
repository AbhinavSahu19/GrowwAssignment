package com.example.stocksapp.presentation

sealed class Screen(val route: String) {
    object Explore : Screen("explore")
    object Search : Screen("search")
    object StockDetail : Screen("stockDetail/{symbol}") {
        fun createRoute(symbol: String) = "stockDetail/$symbol"
    }
    object ViewAll : Screen("viewAll/{type}") {
        fun createRoute(type: String) = "viewAll/$type"
    }
}
