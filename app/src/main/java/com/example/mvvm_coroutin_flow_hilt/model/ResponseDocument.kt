package com.example.mvvm_coroutin_flow_hilt.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

@IgnoreExtraProperties
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
) {
    constructor() : this(ArrayList(), "", "", 0, "", 0, "", "", "", ArrayList(), "") {}

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "authors" to authors,
            "contents" to contents,
            "isbn" to isbn,
            "price" to price,
            "publisher" to publisher,
            "sale_price" to sale_price,
            "status" to status,
            "thumbnail" to thumbnail,
            "title" to title,
            "translators" to translators,
            "url" to url
            )
    }
}

