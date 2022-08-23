package com.example.mvvm_coroutin_flow_hilt.module

import androidx.viewbinding.BuildConfig
import com.example.mvvm_coroutin_flow_hilt.network.ApiLogin
import com.example.mvvm_coroutin_flow_hilt.repository.MainRepository
import com.example.mvvm_coroutin_flow_hilt.network.ApiService
import com.example.mvvm_coroutin_flow_hilt.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

