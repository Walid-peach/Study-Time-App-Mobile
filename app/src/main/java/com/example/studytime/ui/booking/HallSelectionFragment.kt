package com.example.studytime.ui.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.studytime.R
import com.example.studytime.databinding.FragmentHallSelectionBinding
import com.example.studytime.utils.Resource
import com.example.studytime.utils.gone
import com.example.studytime.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HallSelectionFragment : Fragment() {

    private var _binding: FragmentHallSelectionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookingViewModel by activityViewModels()
    private val adapter = HallAdapter { hall ->
        viewModel.selectedHall = hall
        findNavController().navigate(R.id.action_hallSelection_to_tableSelection)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHallSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerHalls.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener { viewModel.loadHalls() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.halls.collect { state ->
                binding.swipeRefresh.isRefreshing = false
                when (state) {
                    is Resource.Loading -> {
                        binding.progressBar.visible()
                        binding.recyclerHalls.gone()
                        binding.tvError.gone()
                    }
                    is Resource.Success -> {
                        binding.progressBar.gone()
                        binding.recyclerHalls.visible()
                        binding.tvError.gone()
                        adapter.submitList(state.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.gone()
                        binding.recyclerHalls.gone()
                        binding.tvError.visible()
                        binding.tvError.text = state.message
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
