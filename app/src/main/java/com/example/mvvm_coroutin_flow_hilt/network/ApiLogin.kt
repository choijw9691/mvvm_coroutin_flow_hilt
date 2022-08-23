package com.example.mvvm_coroutin_flow_hilt.network


import com.google.gson.JsonElement
import org.json.JSONObject
import retrofit2.http.*

interface ApiLogin {
    @Headers("content-type: application/json")
    @POST("/login")
    suspend fun insertUserInfo(
        @Query("id") id : String,
        @Query("pw") pw : String
    ): JsonElement
}



