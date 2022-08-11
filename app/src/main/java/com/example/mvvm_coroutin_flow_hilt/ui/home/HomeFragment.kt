package com.example.mvvm_coroutin_flow_hilt.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_coroutin_flow_hilt.databinding.FragmentHomeBinding
import com.example.mvvm_coroutin_flow_hilt.ui.dashboard.DashboardViewModel
import com.example.mvvm_coroutin_flow_hilt.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

private var _binding: FragmentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
    val homeViewModel: HomeViewModel by activityViewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {


    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root

      binding.vm = homeViewModel



    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}