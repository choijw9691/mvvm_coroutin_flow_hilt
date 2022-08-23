package com.example.mvvm_coroutin_flow_hilt

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.mvvm_coroutin_flow_hilt.adapter.ViewPagerAdapter
import com.example.mvvm_coroutin_flow_hilt.databinding.ActivityMainBinding
import com.example.mvvm_coroutin_flow_hilt.ui.common.PayActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("start","mainactvitystart")

        binding.viewpager.adapter = ViewPagerAdapter(this)
        binding.viewpager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.navView.menu.getItem(position).isChecked = true
                }
            }

        )
        binding.navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_dashboard -> {
                    // ViewPager의 현재 item에 첫 번째 화면을 대입
                    binding.viewpager.currentItem = 1
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_home -> {
                    // ViewPager의 현재 item에 두 번째 화면을 대입
                    binding.viewpager.currentItem = 0
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    // ViewPager의 현재 item에 세 번째 화면을 대입
                    binding.viewpager.currentItem = 2
                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener false
                }
            }
        }

       // val intent = Intent(this, PayActivity::class.java)  // 인텐트를 생성해줌,
      //  startActivity(intent)  // 화면 전환을 시켜줌

    }



}