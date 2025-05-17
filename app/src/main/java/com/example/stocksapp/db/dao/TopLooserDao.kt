package com.example.stocksapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stocksapp.db.entity.TopLooserEntity


@Dao
interface TopLooserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stock: TopLooserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(stocks: List<TopLooserEntity>)

    @Query("SELECT * FROM top_looser")
     suspend fun getAll(): List<TopLooserEntity>

    @Query("SELECT * FROM top_looser LIMIT 6")
    suspend fun get6(): List<TopLooserEntity>

    @Query("DELETE FROM top_looser")
    suspend fun clearAll()
}