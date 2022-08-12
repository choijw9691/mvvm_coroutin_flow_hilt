package com.example.mvvm_coroutin_flow_hilt.ui.common

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mvvm_coroutin_flow_hilt.model.ResponseDocument
import com.example.mvvm_coroutin_flow_hilt.repository.CommonDataBase
import com.example.mvvm_coroutin_flow_hilt.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommonViewModel @Inject constructor(private val repository: CommonDataBase) : ViewModel() {
    private var _toast = MutableSharedFlow<String>()
    val toast =_toast.asSharedFlow()

    fun insert(responseDocument: ResponseDocument){
        Log.d("JIWOUNG","insert1")
        repository.insert(responseDocument)
            .addOnSuccessListener {
                Log.d("JIWOUNG","insert2")

                viewModelScope.launch{
                    Log.d("JIWOUNG","insert5")

                    _toast.emit("insert 성공")
                  }
        }.addOnSuccessListener {
                Log.d("JIWOUNG","insert3")

                viewModelScope.launch {
                    Log.d("JIWOUNG","insert5")

                    _toast.emit("insert 실패")
                }
        }
    }


}