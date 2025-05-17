package com.example.stocksapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stocksapp.db.entity.SearchKeywordEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface SearchKeywordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(keyword: SearchKeywordEntity)

    @Query("SELECT * FROM search_keyword ORDER BY storedAt DESC")
    fun getAll(): Flow<List<SearchKeywordEntity>>

    @Query("SELECT * FROM search_keyword where symbol LIKE '%' || :keyword || '%' ORDER BY storedAt DESC")
    fun getByKeyword(keyword: String): Flow<List<SearchKeywordEntity>>
}