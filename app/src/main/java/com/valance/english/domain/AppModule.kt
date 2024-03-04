package com.valance.english.domain

import com.valance.english.db.repository.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    @Binds
    fun bindAppRepository(appRepositoryImpl: AppRepositoryImpl): AppRepository
}