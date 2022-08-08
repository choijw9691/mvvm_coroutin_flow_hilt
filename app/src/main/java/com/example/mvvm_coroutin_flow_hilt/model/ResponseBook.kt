package com.example.mvvm_coroutin_flow_hilt.model

import com.google.gson.annotations.SerializedName

data class ResponseBook (

        @SerializedName("meta")
        val metaData: MetaData?,

        @SerializedName("documents")
        val documents: List<ResponseDocument>
)