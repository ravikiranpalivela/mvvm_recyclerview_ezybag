package com.ravikiran.recyclerviewexample.data.di


import com.ravikiran.recyclerviewexample.data.remote.ApiService
import com.ravikiran.recyclerviewexample.data.repository.datasource.ApiRemoteDataSource
import com.ravikiran.recyclerviewexample.data.repository.impl.ApiRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Singleton
    @Provides
    fun provideApiRemoteDataSource(apiService: ApiService): ApiRemoteDataSource {
        return ApiRemoteDataSourceImpl(apiService)
    }
}