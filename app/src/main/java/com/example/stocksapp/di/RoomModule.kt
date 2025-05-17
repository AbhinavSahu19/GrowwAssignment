package com.example.stocksapp.di

import android.content.Context
import androidx.room.Room
import com.example.stocksapp.db.StocksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideJobDatabase(@ApplicationContext context: Context): StocksDatabase {
        return Room.databaseBuilder(
            context,
            StocksDatabase::class.java,
            "stocks.db"
        ).build()
    }
}