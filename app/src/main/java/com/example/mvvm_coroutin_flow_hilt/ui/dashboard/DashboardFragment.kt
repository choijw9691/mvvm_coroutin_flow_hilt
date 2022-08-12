package com.example.mvvm_coroutin_flow_hilt.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvm_coroutin_flow_hilt.adapter.dashboardPagingAdapter
import com.example.mvvm_coroutin_flow_hilt.databinding.FragmentDashboardBinding
import com.example.mvvm_coroutin_flow_hilt.ui.common.CommonViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    val dashboardViewModel: DashboardViewModel by activityViewModels()
    val commonViewModel: CommonViewModel by activityViewModels()

    private  val adapter: dashboardPagingAdapter = dashboardPagingAdapter { item -> commonViewModel.insert(item) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 4)

        // collectLatest - 데이터 처리하는 도중 새로운 데이터가 들어올 경우 이전 데이터 처리 취소
        viewLifecycleOwner.lifecycleScope.launch{
            dashboardViewModel.myCustomPosts?.collectLatest {
                adapter.submitData(it)
            }
            commonViewModel.toast.collectLatest {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root



   return root
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}