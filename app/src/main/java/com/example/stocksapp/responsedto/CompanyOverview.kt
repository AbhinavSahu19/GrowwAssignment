package com.example.stocksapp.responsedto

import com.google.gson.annotations.SerializedName


data class CompanyOverview(
    @SerializedName("Symbol")
    var symbol: String,
    @SerializedName("AssetType")
    var assetType: String,
    @SerializedName("Name")
    var name: String,
    @SerializedName("Description")
    var description: String,
    @SerializedName("CIK")
    var cik: String,
    @SerializedName("Exchange")
    var exchange: String,
    @SerializedName("Currency")
    var currency: String,
    @SerializedName("Country")
    var country: String,
    @SerializedName("Sector")
    var sector: String,
    @SerializedName("Industry")
    var industry: String,
    @SerializedName("Address")
    var address: String,
    @SerializedName("OfficialSite")
    var officialSite: String,
    @SerializedName("FiscalYearEnd")
    var fiscalYearEnd: String,
    @SerializedName("LatestQuarter")
    var latestQuarter: String,
    @SerializedName("MarketCapitalization")
    var marketCapitalization: String,
    @SerializedName("EBITDA")
    var ebitda: String,
    @SerializedName("PERatio")
    var PERatio: String,
    @SerializedName("PEGRatio")
    var PEGRatio: String,
    @SerializedName("BookValue")
    var bookValue: String,
    @SerializedName("DividendPerShare")
    var dividendPerShare: String,
    @SerializedName("DividendYield")
    var dividendYield: String,
    @SerializedName("EPS")
    var eps: String,
    @SerializedName("RevenuePerShareTTM")
    var revenuePerShareTTM: String,
    @SerializedName("ProfitMargin")
    var profitMargin: String,
    @SerializedName("OperatingMarginTTM")
    var operatingMarginTTM: String,
    @SerializedName("ReturnOnAssetsTTM")
    var returnOnAssetsTTM: String,
    @SerializedName("ReturnOnEquityTTM")
    var returnOnEquityTTM: String,
    @SerializedName("RevenueTTM")
    var revenueTTM: String,
    @SerializedName("GrossProfitTTM")
    var grossProfitTTM: String,
    @SerializedName("DilutedEPSTTM")
    var dilutedEPSTTM: String,
    @SerializedName("QuarterlyEarningsGrowthYOY")
    var quarterlyEarningsGrowthYOY: String,
    @SerializedName("QuarterlyRevenueGrowthYOY")
    var quarterlyRevenueGrowthYOY: String,
    @SerializedName("AnalystTargetPrice")
    var analystTargetPrice: String,
    @SerializedName("AnalystRatingStrongBuy")
    var analystRatingStrongBuy: String,
    @SerializedName("AnalystRatingBuy")
    var analystRatingBuy: String,
    @SerializedName("AnalystRatingHold")
    var analystRatingHold: String,
    @SerializedName("AnalystRatingSell")
    var analystRatingSell: String,
    @SerializedName("AnalystRatingStrongSell")
    var analystRatingStrongSell: String,
    @SerializedName("TrailingPE")
    var trailingPE: String,
    @SerializedName("ForwardPE")
    var forwardPE: String,
    @SerializedName("PriceToSalesRatioTTM")
    var priceToSalesRatioTTM: String,
    @SerializedName("PriceToBookRatio")
    var priceToBookRatio: String,
    @SerializedName("EVToRevenue")
    var EVToRevenue: String,
    @SerializedName("EVToEBITDA")
    var EVToEBITDA: String,
    @SerializedName("Beta")
    var beta: String,
    @SerializedName("52WeekHigh")
    var weekHigh52: String,
    @SerializedName("52WeekLow")
    var weekLow52: String,
    @SerializedName("50DayMovingAverage")
    var dayMovingAverage50: String,
    @SerializedName("200DayMovingAverage")
    var dayMovingAverage200: String,
    @SerializedName("SharesOutstanding")
    var sharesOutstanding: String,
    @SerializedName("DividendDate")
    var dividendDate: String,
    @SerializedName("ExDividendDate")
    var exDividendDate: String
)
