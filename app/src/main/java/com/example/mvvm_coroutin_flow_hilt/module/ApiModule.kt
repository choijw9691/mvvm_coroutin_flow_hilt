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
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class type1

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class type2

    @type1
    @Provides
    fun provideBaseUrl() = "https://dapi.kakao.com/"

    @type2
    @Provides
    fun provideBaseUrl1() = "http://10.0.2.2:8001/"

    @type1
    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient.Builder().build()
    }

    @type1
    @Singleton
    @Provides
    fun provideRetrofit(@type1 okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(provideBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @type1
    @Singleton
    @Provides
    fun provideApiService(@type1 retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    @type1
    @Provides
    @Singleton
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO
    @type1
    @Singleton
    @Provides
    fun provideMainRepository(@type1 apiService: ApiService, @type1 ioDispatcher: CoroutineDispatcher) =
        MainRepository(apiService, ioDispatcher)

    @type2
    @Singleton
    @Provides
    fun provideOkHttpClient1() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient.Builder().build()
    }

    @type2
    @Singleton
    @Provides
    fun provideRetrofit1(@type2 okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(provideBaseUrl1())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @type2
    @Singleton
    @Provides
    fun provideApiService1(@type2 retrofit: Retrofit): ApiLogin {
        return retrofit.create(ApiLogin::class.java)
    }

    @type2
    @Singleton
    @Provides
    fun provideMainRepository1() =
        LoginRepository()




}