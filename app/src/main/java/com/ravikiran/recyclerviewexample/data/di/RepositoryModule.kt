package com.ravikiran.recyclerviewexample.data.di


import com.ravikiran.recyclerviewexample.data.repository.ApiRepository
import com.ravikiran.recyclerviewexample.data.repository.ApiRepositoryImpl
import com.ravikiran.recyclerviewexample.data.repository.datasource.ApiRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesNewsRepository(
        newsRemoteDataSource: ApiRemoteDataSource,
    ): ApiRepository {
        return ApiRepositoryImpl(newsRemoteDataSource)
    }
}