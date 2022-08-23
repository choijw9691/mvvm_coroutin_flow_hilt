package com.example.mvvm_coroutin_flow_hilt.repository

import android.util.Log
import android.widget.Toast
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mvvm_coroutin_flow_hilt.model.ResponseDocument
import com.example.mvvm_coroutin_flow_hilt.module.ApiModule
import com.example.mvvm_coroutin_flow_hilt.network.ApiLogin
import com.example.mvvm_coroutin_flow_hilt.network.ApiService
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginRepository @Inject constructor(
) {
    private var firebaseAuth: FirebaseAuth = Firebase.auth


    fun login(email: String, password: String): Task<AuthResult> {
       return firebaseAuth.signInWithEmailAndPassword(email, password)
    }

     fun register(email: String, password: String): Task<AuthResult> {

         return firebaseAuth.createUserWithEmailAndPassword(email, password)
    }

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser
}