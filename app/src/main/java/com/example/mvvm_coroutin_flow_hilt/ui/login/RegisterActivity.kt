package com.example.mvvm_coroutin_flow_hilt.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.mvvm_coroutin_flow_hilt.R
import com.example.mvvm_coroutin_flow_hilt.databinding.ActivityLoginBinding
import com.example.mvvm_coroutin_flow_hilt.databinding.ActivityRegisterBinding
import com.example.mvvm_coroutin_flow_hilt.ui.common.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    val registerViewModel: RegisterViewModel by viewModels()
    var token = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        val view = binding.root
        setContentView(view)

        binding.vm = registerViewModel
        binding.registerActivity = this
        token = "123455667"
        lifecycleScope.launch {
            launch {
                registerViewModel.isResgisterSuccessful.collectLatest {

                    when (it) {
                        is NetworkResult.Loading -> {
                        }
                        is NetworkResult.Failure -> {
                            Toast.makeText(this@RegisterActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                       Log.d("JIWOUNG",it.errorMessage)
                        finish()
                        }
                        is NetworkResult.Success -> {
                            Toast.makeText(this@RegisterActivity, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        finish()
                        }
                    }
                }
            }
        }
}}