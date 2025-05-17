package com.example.stocksapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stocksapp.db.entity.TopGainerEntity


@Dao
interface TopGainerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stock: TopGainerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(stocks: List<TopGainerEntity>)

    @Query("SELECT * FROM top_gainer")
    suspend fun getAll(): List<TopGainerEntity>

    @Query("SELECT * FROM top_gainer LIMIT 6")
    suspend fun get6(): List<TopGainerEntity>

    @Query("DELETE FROM top_gainer")
    suspend fun clearAll()
}