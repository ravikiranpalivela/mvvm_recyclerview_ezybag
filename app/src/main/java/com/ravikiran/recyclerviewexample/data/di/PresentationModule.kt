package com.ravikiran.recyclerviewexample.data.di

import android.app.Application
import com.ravikiran.recyclerviewexample.adapter.CategoryAdapter
import com.ravikiran.recyclerviewexample.adapter.MainAdapter
import com.ravikiran.recyclerviewexample.adapter.SubCategoryAdapter
import com.ravikiran.recyclerviewexample.data.usecase.*
import com.ravikiran.recyclerviewexample.viewmodel.SharedViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {

    @Singleton
    @Provides
    fun providesMainViewModelFactory(
        getMainPageUseCase: GetMainPageUseCase,
        getLoginUseCase: GetLoginUseCase,
        getRegisterUseCase: GetRegisterUseCase,
        getSubCatPageUseCase: GetSubCatPageUseCase,
        getProductPageUseCase: GetProductPageUseCase,
        app: Application
    ): SharedViewModelFactory {
        return SharedViewModelFactory(
            getMainPageUseCase,
            getLoginUseCase,
            getRegisterUseCase,
            getSubCatPageUseCase,
            getProductPageUseCase,
            app
        )
    }

    @Provides
    fun provideMainAdapter(): MainAdapter {
        return MainAdapter()
    }

    @Provides
    fun provideCategoryAdapter(): CategoryAdapter {
        return CategoryAdapter()
    }

    @Provides
    fun provideSubCategoryAdapter(): SubCategoryAdapter {
        return SubCategoryAdapter()
    }
}