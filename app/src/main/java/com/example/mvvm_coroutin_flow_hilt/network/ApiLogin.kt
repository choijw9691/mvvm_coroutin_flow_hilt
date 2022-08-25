package com.example.mvvm_coroutin_flow_hilt.network


import com.google.gson.JsonElement
import org.json.JSONObject
import retrofit2.http.*

 interface ApiLogin {
    @Headers("content-type: application/json")
    @POST("/login")
    suspend fun insertUserInfo(
        @Query("token") token: String,
        @Query("name") name: String,
        @Query("id") id: String,
        @Query("pw") pw: String,
        @Query("level") level: String
    ): JsonElement

     @Headers("content-type: application/json")
     @POST("/")
     suspend fun hometest(

         @Query("id") id: String

     ): JsonElement
}



