package com.example.mvvm_coroutin_flow_hilt.network

import android.provider.SyncStateContract
import com.example.mvvm_coroutin_flow_hilt.model.ResponseBook
import com.example.mvvm_coroutin_flow_hilt.model.User
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("/v3/search/book")
    suspend fun getBookResponse(
        @Header("Authorization") apiKey: String = "KakaoAK 9dda44542ed4491dd2f636cb60c5e52e",
        @Query("query") query : String,
        @Query("page") page : Int
    ): ResponseBook

}