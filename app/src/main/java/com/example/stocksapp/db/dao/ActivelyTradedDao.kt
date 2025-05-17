package com.example.stocksapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stocksapp.db.entity.ActivelyTradedEntity


@Dao
interface ActivelyTradedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stock: ActivelyTradedEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(stocks: List<ActivelyTradedEntity>)

    @Query("SELECT * FROM actively_traded")
    suspend fun getAll(): List<ActivelyTradedEntity>

    @Query("SELECT * FROM actively_traded LIMIT 6")
    suspend fun get6(): List<ActivelyTradedEntity>

    @Query("DELETE FROM actively_traded")
    suspend fun clearAll()
}