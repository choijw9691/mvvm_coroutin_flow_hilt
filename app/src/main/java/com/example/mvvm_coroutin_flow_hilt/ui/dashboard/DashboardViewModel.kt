package com.example.mvvm_coroutin_flow_hilt.ui.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_coroutin_flow_hilt.model.ResponseBook
import com.example.mvvm_coroutin_flow_hilt.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {


    private var _text = MutableStateFlow("This is dashboard Fragment")
    var text = _text.asStateFlow().value
    private var _myCustomPosts = MutableStateFlow<ResponseBook>(ResponseBook(null,null))
    val myCustomPosts: MutableStateFlow<ResponseBook>
    get() = _myCustomPosts

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
          repository.getBookResponse("사랑").collect {
              _myCustomPosts?.value = it.body()!!
              Log.d("responseResult", it.body().toString())
              Log.d("responseResult", _myCustomPosts.value.toString())
          }
        }
    }
}