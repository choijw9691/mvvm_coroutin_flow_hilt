package com.example.mvvm_coroutin_flow_hilt.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mvvm_coroutin_flow_hilt.model.ResponseDocument
import com.example.mvvm_coroutin_flow_hilt.repository.LoginRepository
import com.example.mvvm_coroutin_flow_hilt.repository.MainRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class loginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {


    fun register(email: String, password: String) : Task<AuthResult> {
        return  repository.register(email, password)
    }

}



