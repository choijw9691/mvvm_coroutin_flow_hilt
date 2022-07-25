package com.example.mvvm_coroutin_flow_hilt.ui.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_coroutin_flow_hilt.model.ResponseBook
import com.example.mvvm_coroutin_flow_hilt.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {


    private var _text = MutableStateFlow("This is dashboard Fragment")
    var text = _text.asStateFlow().value
    private var _myCustomPosts: MutableStateFlow<Response<ResponseBook>>? = null
    var myCustomPosts = _myCustomPosts?.asStateFlow()?.value

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val response = repository.getBookResponse("사랑")
            Log.d("responseResult",response.body().toString())
            _myCustomPosts?.value = response
        }
    }
}