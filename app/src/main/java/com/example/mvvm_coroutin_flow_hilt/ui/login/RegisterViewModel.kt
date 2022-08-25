package com.example.mvvm_coroutin_flow_hilt.ui.login

import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class RegisterViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

    private var _isResgisterSuccessful = MutableSharedFlow<NetworkResult<JsonElement>>()
    val isResgisterSuccessful = _isResgisterSuccessful.asSharedFlow()

    fun register(token: String, name: String, id: String, pw: String, level: String) {

/*        repository.register(token, name, id, pw, level).addOnCompleteListener {
            viewModelScope.launch {
                _isResgisterSuccessful.emit(it.isSuccessful)
                var a = apiService.insertUserInfo(email, password)
                Log.d("JIWOUNG", "responsetest: ${a.asJsonObject.get("success")}")
            }
        }*/
        viewModelScope.launch {
            repository.register(token, name, id, pw, level).collect {
                _isResgisterSuccessful.emit(it)
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