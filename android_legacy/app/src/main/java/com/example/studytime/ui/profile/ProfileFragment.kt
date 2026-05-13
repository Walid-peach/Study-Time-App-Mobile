package com.example.studytime.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.studytime.R
import com.example.studytime.data.model.User
import com.example.studytime.databinding.FragmentProfileBinding
import com.example.studytime.utils.Resource
import com.example.studytime.utils.gone
import com.example.studytime.utils.setError
import com.example.studytime.utils.trimText
import com.example.studytime.utils.visible
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()
    private var isEditing = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setEditMode(false)

        binding.btnEdit.setOnClickListener {
            if (isEditing) saveProfile() else setEditMode(true)
        }
        binding.btnCancelEdit.setOnClickListener { setEditMode(false) }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.user.collect { state ->
                when (state) {
                    is Resource.Loading -> {
                        binding.progressBar.visible()
                        binding.contentLayout.gone()
                    }
                    is Resource.Success -> {
                        binding.progressBar.gone()
                        binding.contentLayout.visible()
                        populateFields(state.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.gone()
                        Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.updateState.collect { state ->
                when (state) {
                    is Resource.Loading -> {
                        binding.btnEdit.isEnabled = false
                    }
                    is Resource.Success -> {
                        binding.btnEdit.isEnabled = true
                        setEditMode(false)
                        Snackbar.make(binding.root, getString(R.string.profile_updated), Snackbar.LENGTH_SHORT).show()
                        viewModel.resetUpdateState()
                    }
                    is Resource.Error -> {
                        binding.btnEdit.isEnabled = true
                        Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                        viewModel.resetUpdateState()
                    }
                    null -> binding.btnEdit.isEnabled = true
                }
            }
        }
    }

    private fun populateFields(user: User) {
        binding.etFullName.setText(user.fullName)
        binding.etEmail.setText(user.email)
        binding.etDepartment.setText(user.department)
    }

    private fun setEditMode(editing: Boolean) {
        isEditing = editing
        binding.etFullName.isEnabled = editing
        binding.etDepartment.isEnabled = editing
        binding.btnEdit.text = getString(if (editing) R.string.save else R.string.edit_profile)
        binding.btnCancelEdit.visibility = if (editing) View.VISIBLE else View.GONE
    }

    private fun saveProfile() {
        val fullName = binding.etFullName.trimText()
        val department = binding.etDepartment.trimText()

        if (fullName.isEmpty()) {
            binding.tilFullName.setError(getString(R.string.error_field_required))
            return
        }
        binding.tilFullName.setError(null)

        viewModel.updateProfile(fullName, department)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
