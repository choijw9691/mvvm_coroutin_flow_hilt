package com.example.mvvm_coroutin_flow_hilt.model

import android.net.Uri
import com.google.gson.annotations.SerializedName

data class User (
    var token: String,
    var name: String,
    var id: String,
    var pw: String,
    var level: String
)

