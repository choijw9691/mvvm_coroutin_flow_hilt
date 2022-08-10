package com.example.mvvm_coroutin_flow_hilt.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.example.mvvm_coroutin_flow_hilt.R
import com.example.mvvm_coroutin_flow_hilt.databinding.ActivityLoginBinding
import com.example.mvvm_coroutin_flow_hilt.databinding.ActivityMainBinding
import com.example.mvvm_coroutin_flow_hilt.ui.dashboard.DashboardViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    val loginViewModel: loginViewModel by viewModels()
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = Firebase.auth

        binding.id.setOnClickListener {
/*
         loginViewModel.register(
                    binding.idEditText.text.toString(), "1234"
                ).addOnCompleteListener {
            if ( it.isSuccessful){
                Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()

            }
         }
*/
            firebaseAuth.createUserWithEmailAndPassword( binding.idEditText.text.toString(),  "123456").addOnCompleteListener(this) {
                if ( it.isSuccessful){
                    Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
Log.d("JIWOUNG","taskcheck: "+it.exception.toString())
                }
        }

    }
}}