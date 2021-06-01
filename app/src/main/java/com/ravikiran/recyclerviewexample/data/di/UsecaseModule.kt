package com.ravikiran.recyclerviewexample.data.di
import com.ravikiran.recyclerviewexample.data.repository.ApiRepository
import com.ravikiran.recyclerviewexample.data.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providesGetMainPageUseCase(apiRepository: ApiRepository): GetMainPageUseCase{
        return GetMainPageUseCase(apiRepository)
    }

    @Singleton
    @Provides
    fun providesGetLoginUseCase(apiRepository: ApiRepository): GetLoginUseCase{
        return GetLoginUseCase(apiRepository)
    }

    @Singleton
    @Provides
    fun providesGetRegisterUseCase(apiRepository: ApiRepository): GetRegisterUseCase{
        return GetRegisterUseCase(apiRepository)
    }
}