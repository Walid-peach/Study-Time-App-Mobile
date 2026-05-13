package com.example.studytime.ui.dashboard

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.studytime.R
import com.example.studytime.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_dashboard, menu)
            }
            override fun onMenuItemSelected(item: MenuItem): Boolean {
                return when (item.itemId) {
                    R.id.action_logout -> {
                        viewModel.signOut()
                        findNavController().navigate(R.id.action_dashboard_to_login)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.btnBookNow.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_hallSelection)
        }
        binding.btnMyBookings.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_myBookings)
        }
        binding.btnProfile.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_profile)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.user.collect { user ->
                val name = user?.fullName?.split(" ")?.firstOrNull() ?: ""
                binding.tvGreeting.text = if (name.isNotEmpty())
                    getString(R.string.greeting_name, name)
                else
                    getString(R.string.greeting_default)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
