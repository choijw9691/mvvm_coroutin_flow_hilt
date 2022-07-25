package com.example.mvvm_coroutin_flow_hilt.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm_coroutin_flow_hilt.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val dashboardViewModel: DashboardViewModel by viewModels()
        val textView: TextView = binding.textDashboard

        var test=dashboardViewModel.myCustomPosts
        textView.text = test.toString()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}