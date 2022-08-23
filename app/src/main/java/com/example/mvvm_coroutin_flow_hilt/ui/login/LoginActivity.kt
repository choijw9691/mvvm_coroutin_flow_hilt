package com.example.mvvm_coroutin_flow_hilt.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.mvvm_coroutin_flow_hilt.MainActivity
import com.example.mvvm_coroutin_flow_hilt.R
import com.example.mvvm_coroutin_flow_hilt.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val view = binding.root
        setContentView(view)
        binding.vm = loginViewModel

        if (loginViewModel.isLogin()) {
            val intent: Intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        lifecycleScope.launch {
            launch {
                loginViewModel.isResgisterSuccessful.collectLatest {
                    if (it) {
                        Toast.makeText(this@LoginActivity, "회원가입 성공", Toast.LENGTH_SHORT).show()

                    } else Toast.makeText(this@LoginActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }
            launch {
                loginViewModel.isLoginSuccessful.collectLatest {
                    if (it) {
                        Toast.makeText(this@LoginActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                        val intent: Intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else Toast.makeText(this@LoginActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    init {
        Firebase.auth.signOut()

    }
}