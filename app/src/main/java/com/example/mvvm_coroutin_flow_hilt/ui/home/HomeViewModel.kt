package com.example.mvvm_coroutin_flow_hilt.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_coroutin_flow_hilt.repository.LoginRepository
import com.example.mvvm_coroutin_flow_hilt.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {

    var user= repository.currentUser()

    fun getUserName() = if (user?.displayName!=null) user?.displayName else user?.uid.toString()

    fun getUseremail() = user?.email

    fun getUserphotoUrl() = user?.photoUrl

    fun getUseremailVerifiede() = user?.isEmailVerified.toString()

    fun getUseruid() = user?.uid


}