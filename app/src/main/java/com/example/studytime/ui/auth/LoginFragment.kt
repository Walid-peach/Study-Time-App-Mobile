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
import com.example.studytime.databinding.FragmentLoginBinding
import com.example.studytime.utils.Resource
import com.example.studytime.utils.gone
import com.example.studytime.utils.setError
import com.example.studytime.utils.trimText
import com.example.studytime.utils.visible
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.isLoggedIn()) {
            binding.root.post { findNavController().navigate(R.id.action_login_to_dashboard) }
            return
        }

        binding.btnLogin.setOnClickListener { attemptLogin() }
        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_reset)
        }
        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginState.collect { state ->
                when (state) {
                    is Resource.Loading -> {
                        binding.progressBar.visible()
                        binding.btnLogin.isEnabled = false
                    }
                    is Resource.Success -> {
                        binding.progressBar.gone()
                        findNavController().navigate(R.id.action_login_to_dashboard)
                    }
                    is Resource.Error -> {
                        binding.progressBar.gone()
                        binding.btnLogin.isEnabled = true
                        Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                    }
                    null -> {
                        binding.progressBar.gone()
                        binding.btnLogin.isEnabled = true
                    }
                }
            }
        }
    }

    private fun attemptLogin() {
        val email = binding.etEmail.trimText()
        val password = binding.etPassword.trimText()
        var valid = true

        if (email.isEmpty()) {
            binding.tilEmail.setError(getString(R.string.error_field_required))
            valid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.setError(getString(R.string.error_invalid_email))
            valid = false
        } else {
            binding.tilEmail.setError(null)
        }

        if (password.isEmpty()) {
            binding.tilPassword.setError(getString(R.string.error_field_required))
            valid = false
        } else {
            binding.tilPassword.setError(null)
        }

        if (valid) viewModel.login(email, password)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
