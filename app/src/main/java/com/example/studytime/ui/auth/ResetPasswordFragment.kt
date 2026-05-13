package com.example.studytime.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.studytime.R
import com.example.studytime.data.repository.AuthRepository
import com.example.studytime.databinding.FragmentResetPasswordBinding
import com.example.studytime.utils.Resource
import com.example.studytime.utils.gone
import com.example.studytime.utils.setError
import com.example.studytime.utils.trimText
import com.example.studytime.utils.visible
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ResetPasswordFragment : Fragment() {

    private var _binding: FragmentResetPasswordBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var authRepo: AuthRepository
    private val resetState = MutableStateFlow<Resource<Unit>?>(null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSendReset.setOnClickListener {
            val email = binding.etEmail.trimText()
            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.tilEmail.setError(getString(R.string.error_invalid_email))
                return@setOnClickListener
            }
            binding.tilEmail.setError(null)
            viewLifecycleOwner.lifecycleScope.launch {
                resetState.value = Resource.Loading
                resetState.value = authRepo.sendPasswordReset(email)
            }
        }

        binding.tvBackToLogin.setOnClickListener { findNavController().navigateUp() }

        viewLifecycleOwner.lifecycleScope.launch {
            resetState.collect { state ->
                when (state) {
                    is Resource.Loading -> {
                        binding.progressBar.visible()
                        binding.btnSendReset.isEnabled = false
                    }
                    is Resource.Success -> {
                        binding.progressBar.gone()
                        Snackbar.make(binding.root, getString(R.string.reset_email_sent), Snackbar.LENGTH_LONG).show()
                        findNavController().navigateUp()
                    }
                    is Resource.Error -> {
                        binding.progressBar.gone()
                        binding.btnSendReset.isEnabled = true
                        Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                    }
                    null -> {
                        binding.progressBar.gone()
                        binding.btnSendReset.isEnabled = true
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
