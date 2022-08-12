package com.example.mvvm_coroutin_flow_hilt.module

import com.example.mvvm_coroutin_flow_hilt.repository.CommonDataBase
import com.example.mvvm_coroutin_flow_hilt.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Singleton
    @Provides
    fun provideMainRepository()= CommonDataBase()
}