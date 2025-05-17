package com.example.stocksapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stocksapp.db.dao.ActivelyTradedDao
import com.example.stocksapp.db.dao.CompanyOverviewDao
import com.example.stocksapp.db.dao.SearchKeywordDao
import com.example.stocksapp.db.dao.TopGainerDao
import com.example.stocksapp.db.dao.TopLooserDao
import com.example.stocksapp.db.entity.ActivelyTradedEntity
import com.example.stocksapp.db.entity.CompanyOverviewEntity
import com.example.stocksapp.db.entity.SearchKeywordEntity
import com.example.stocksapp.db.entity.TopGainerEntity
import com.example.stocksapp.db.entity.TopLooserEntity

@Database(
    entities = [
        SearchKeywordEntity::class,
        TopGainerEntity::class,
        TopLooserEntity::class,
        ActivelyTradedEntity::class,
        CompanyOverviewEntity::class
    ],
    version = 1
)
abstract class StocksDatabase : RoomDatabase() {
    abstract val searchKeywordsDao: SearchKeywordDao
    abstract val topGainerDao: TopGainerDao
    abstract val topLooserDao: TopLooserDao
    abstract val activelyTradedDao: ActivelyTradedDao
    abstract val companyOverviewDao: CompanyOverviewDao
}