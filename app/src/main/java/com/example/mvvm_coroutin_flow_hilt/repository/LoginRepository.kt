package com.example.mvvm_coroutin_flow_hilt.repository

import android.util.Log
import android.widget.Toast
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mvvm_coroutin_flow_hilt.model.ResponseDocument
import com.example.mvvm_coroutin_flow_hilt.model.User
import com.example.mvvm_coroutin_flow_hilt.module.ApiModule
import com.example.mvvm_coroutin_flow_hilt.network.ApiLogin
import com.example.mvvm_coroutin_flow_hilt.network.ApiService
import com.example.mvvm_coroutin_flow_hilt.ui.common.NetworkResult
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.JsonElement
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginRepository @Inject constructor(
    @ApiModule.type2 private val apiService: ApiLogin
) {
    private var firebaseAuth: FirebaseAuth = Firebase.auth


    fun login(email: String, password: String): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(email, password)
    }

    suspend fun register(token: String, name: String, id: String, pw: String, level: String) =
        flow {
            emit(NetworkResult.Loading(true))
            val response = apiService.insertUserInfo(token, name, id, pw, level)
            emit(NetworkResult.Success(response.asJsonObject.get("success")))
            //  return firebaseAuth.createUserWithEmailAndPassword(email, password)
        }.catch { e ->
            emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
        }

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser
}