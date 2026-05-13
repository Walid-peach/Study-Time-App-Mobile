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
import com.example.studytime.databinding.FragmentRegisterBinding
import com.example.studytime.utils.Resource
import com.example.studytime.utils.gone
import com.example.studytime.utils.setError
import com.example.studytime.utils.trimText
import com.example.studytime.utils.visible
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener { attemptRegister() }
        binding.tvLogin.setOnClickListener { findNavController().navigateUp() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.registerState.collect { state ->
                when (state) {
                    is Resource.Loading -> {
                        binding.progressBar.visible()
                        binding.btnRegister.isEnabled = false
                    }
                    is Resource.Success -> {
                        binding.progressBar.gone()
                        findNavController().navigate(R.id.action_register_to_dashboard)
                    }
                    is Resource.Error -> {
                        binding.progressBar.gone()
                        binding.btnRegister.isEnabled = true
                        Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                    }
                    null -> {
                        binding.progressBar.gone()
                        binding.btnRegister.isEnabled = true
                    }
                }
            }
        }
    }

    private fun attemptRegister() {
        val fullName = binding.etFullName.trimText()
        val email = binding.etEmail.trimText()
        val password = binding.etPassword.trimText()
        val confirmPassword = binding.etConfirmPassword.trimText()
        val department = binding.etDepartment.trimText()
        var valid = true

        if (fullName.isEmpty()) {
            binding.tilFullName.setError(getString(R.string.error_field_required)); valid = false
        } else binding.tilFullName.setError(null)

        if (email.isEmpty()) {
            binding.tilEmail.setError(getString(R.string.error_field_required)); valid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.setError(getString(R.string.error_invalid_email)); valid = false
        } else binding.tilEmail.setError(null)

        if (password.length < 6) {
            binding.tilPassword.setError(getString(R.string.error_password_too_short)); valid = false
        } else binding.tilPassword.setError(null)

        if (confirmPassword != password) {
            binding.tilConfirmPassword.setError(getString(R.string.error_passwords_dont_match)); valid = false
        } else binding.tilConfirmPassword.setError(null)

        if (department.isEmpty()) {
            binding.tilDepartment.setError(getString(R.string.error_field_required)); valid = false
        } else binding.tilDepartment.setError(null)

        if (valid) viewModel.register(fullName, email, password, department)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
