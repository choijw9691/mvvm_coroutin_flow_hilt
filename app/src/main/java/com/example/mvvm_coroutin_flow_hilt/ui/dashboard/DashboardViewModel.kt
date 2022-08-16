package com.example.mvvm_coroutin_flow_hilt.ui.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn

import com.example.mvvm_coroutin_flow_hilt.model.ResponseBook
import com.example.mvvm_coroutin_flow_hilt.model.ResponseDocument
import com.example.mvvm_coroutin_flow_hilt.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private var _myCustomPosts = MutableSharedFlow<PagingData<ResponseDocument>>()
    val myCustomPosts =_myCustomPosts.asSharedFlow().cachedIn(viewModelScope)

    init {
       loadData("소나기")
    }

     fun loadData(query: String){
        viewModelScope.launch {
          repository.getBookResponse(query).collect {
              _myCustomPosts.emit(it)
          }
        }
    }


}