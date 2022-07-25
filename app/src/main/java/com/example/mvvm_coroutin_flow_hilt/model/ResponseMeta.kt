package com.example.mvvm_coroutin_flow_hilt.model

import com.google.gson.annotations.SerializedName

data class MetaData(
    @SerializedName("total_count")
    val totalCount: Int?,

    @SerializedName("is_end")
    val isEnd: Boolean?,

    @SerializedName("pageable_count")
    val pageable_count : Int
)
