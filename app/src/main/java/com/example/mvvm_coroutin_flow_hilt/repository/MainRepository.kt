package com.example.mvvm_coroutin_flow_hilt.repository

import com.example.mvvm_coroutin_flow_hilt.model.ResponseBook
import com.example.mvvm_coroutin_flow_hilt.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getBookResponse(query: String): Response<ResponseBook> =
        apiService.getBookResponse(query = query)

}