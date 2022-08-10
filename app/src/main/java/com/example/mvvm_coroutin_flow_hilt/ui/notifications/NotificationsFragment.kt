package com.example.mvvm_coroutin_flow_hilt.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvm_coroutin_flow_hilt.adapter.dashboardPagingAdapter
import com.example.mvvm_coroutin_flow_hilt.adapter.notificationPagingAdapter
import com.example.mvvm_coroutin_flow_hilt.databinding.FragmentNotificationsBinding
import com.example.mvvm_coroutin_flow_hilt.ui.dashboard.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private  val adapter: notificationPagingAdapter = notificationPagingAdapter { item ->  }

    private val binding get() = _binding!!
    val dashboardViewModel: DashboardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewLifecycleOwner.lifecycleScope.launch {
            dashboardViewModel.favoritesFlow.collectLatest {items->
                Log.d("JIWOUNG", "startstttt")
                adapter.submitList(items)
            }
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 4)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}