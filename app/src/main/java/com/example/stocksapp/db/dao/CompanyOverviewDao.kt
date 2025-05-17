package com.example.stocksapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stocksapp.db.entity.CompanyOverviewEntity

@Dao
interface CompanyOverviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stocks: CompanyOverviewEntity)

    @Query("SELECT * FROM company_overview WHERE symbol = :symbol")
    suspend fun getBySymbol(symbol: String): CompanyOverviewEntity?

    @Query("DELETE FROM company_overview")
    suspend fun clearAll()
}