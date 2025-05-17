package com.example.stocksapp.di

import com.example.stocksapp.repository.Repository
import com.example.stocksapp.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideBlogRepo(repo: RepositoryImpl): Repository
}