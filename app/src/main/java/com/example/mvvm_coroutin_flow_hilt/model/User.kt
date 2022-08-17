package com.example.mvvm_coroutin_flow_hilt.model

import android.net.Uri
import com.google.gson.annotations.SerializedName

data class User (
    val name: String?,
    val email: String?,
    val photoUrl: Uri?,
    val emailVerified: Boolean,
    val uid: String
)

