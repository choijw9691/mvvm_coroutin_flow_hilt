package com.example.mvvm_coroutin_flow_hilt.ui.login

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.EditText
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_coroutin_flow_hilt.module.ApiModule
import com.example.mvvm_coroutin_flow_hilt.network.ApiLogin
import com.example.mvvm_coroutin_flow_hilt.repository.LoginRepository
import com.example.mvvm_coroutin_flow_hilt.ui.common.NetworkResult
import com.google.gson.JsonElement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {


    private var _isLoginSuccessful = MutableSharedFlow<Boolean>()
    val isLoginSuccessful = _isLoginSuccessful.asSharedFlow()


    fun login(email: String, password: String) {
        repository.login(email, password).addOnCompleteListener {
            viewModelScope.launch {
                _isLoginSuccessful.emit(it.isSuccessful)
            }
        }
    }

    fun getText(view: EditText): String {
        return view.text.toString()
    }

    fun isLogin(): Boolean {
        return repository.currentUser() != null
    }
}



