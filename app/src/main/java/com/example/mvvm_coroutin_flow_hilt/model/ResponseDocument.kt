package com.example.mvvm_coroutin_flow_hilt.model

import com.google.gson.annotations.SerializedName

data class ResponseDocument(
    @SerializedName("authors")
    val authors: ArrayList<String>,
    @SerializedName("contents")
    val contents: String,
    @SerializedName("isbn")
    val isbn: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("sale_price")
    val sale_price: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("translators")
    val translators: ArrayList<String>,
    @SerializedName("url")
    val url: String
)

