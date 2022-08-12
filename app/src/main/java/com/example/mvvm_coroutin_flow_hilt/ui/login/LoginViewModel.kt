package com.example.mvvm_coroutin_flow_hilt.ui.login

import android.app.usage.UsageEvents
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mvvm_coroutin_flow_hilt.R
import com.example.mvvm_coroutin_flow_hilt.model.ResponseDocument
import com.example.mvvm_coroutin_flow_hilt.model.User
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
class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {

    private var _isResgisterSuccessful = MutableSharedFlow<Boolean>()
    val isResgisterSuccessful = _isResgisterSuccessful.asSharedFlow()

    private var _isLoginSuccessful = MutableSharedFlow<Boolean>()
    val isLoginSuccessful = _isLoginSuccessful.asSharedFlow()

    fun register(email: String, password: String) {
        repository.register(email, password).addOnCompleteListener {
            viewModelScope.launch {
                _isResgisterSuccessful.emit(it.isSuccessful)
            }
        }
    }

    fun login(email: String, password: String){
        repository.login(email, password).addOnCompleteListener {
            viewModelScope.launch {
                _isLoginSuccessful.emit(it.isSuccessful)
            }        }
    }

    fun getText(view: EditText): String {
        return view.text.toString()
    }

    fun isLogin(): Boolean {
        return repository.currentUser() !=null
    }
}



