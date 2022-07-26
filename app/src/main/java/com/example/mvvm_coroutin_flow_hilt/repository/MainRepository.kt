package com.example.mvvm_coroutin_flow_hilt.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mvvm_coroutin_flow_hilt.model.ResponseBook
import com.example.mvvm_coroutin_flow_hilt.model.ResponseDocument
import com.example.mvvm_coroutin_flow_hilt.module.ApiModule
import com.example.mvvm_coroutin_flow_hilt.network.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(@ApiModule.type1 private val apiService: ApiService, @ApiModule.type1 private val ioDispatcher: CoroutineDispatcher
) {

     fun getBookResponse(query: String) : Flow<PagingData<ResponseDocument>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MainDataSource(query = query,ioDispatcher,apiService)
            }
        ).flow
    }

}