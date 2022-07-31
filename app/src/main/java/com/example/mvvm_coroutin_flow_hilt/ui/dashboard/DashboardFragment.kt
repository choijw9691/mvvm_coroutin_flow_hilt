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
import androidx.lifecycle.lifecycleScope
import com.example.mvvm_coroutin_flow_hilt.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    val dashboardViewModel: DashboardViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView: TextView = binding.textDashboard

        Log.d("JIWOUNG","bodycheck: "+"start");
      // dashboardViewModel.loadData();
        viewLifecycleOwner.lifecycleScope.launch{
            dashboardViewModel.myCustomPosts?.collectLatest {
                Log.d("JIWOUNG", "bodycheck: ${it.documents.toString()}");
                textView.text = it.documents.toString()

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