package com.example.mvvm_coroutin_flow_hilt.adapter

import android.app.Notification
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mvvm_coroutin_flow_hilt.ui.dashboard.DashboardFragment
import com.example.mvvm_coroutin_flow_hilt.ui.home.HomeFragment
import com.example.mvvm_coroutin_flow_hilt.ui.notifications.NotificationsFragment

class ViewPagerAdapter (fragment : FragmentActivity) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> DashboardFragment()
            else -> NotificationsFragment()
        }
    }
}