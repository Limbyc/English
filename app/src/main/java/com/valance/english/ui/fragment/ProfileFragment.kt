package com.valance.english.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.valance.english.databinding.ProfileFragmentBinding
import com.valance.english.db.entity.User
import com.valance.english.ui.viewmodels.ProfileViewModel
import com.valance.english.ui.viewmodels.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: ProfileFragmentBinding
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)

        loadUserFromSharedPreferences()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val maxId = profileViewModel.getMaxUserId()?: 0
                val user = profileViewModel.getUserById(maxId)

                user?.let {
                    binding.name.text = it.name
                    binding.phoneNumber.text = it.phone
                    binding.poshta.text = it.email

                    saveUserToSharedPreferences(it)
                }
            } catch (e: Exception) {
                Log.d("yrueorhe", "пиздец")
            }
        }
    }
    private fun loadUserFromSharedPreferences() {
        val userName = preferences.getString("user_name", "")
        val userPhone = preferences.getString("user_phone", "")
        val userEmail = preferences.getString("user_email", "")

        binding.name.text = userName
        binding.phoneNumber.text = userPhone
        binding.poshta.text = userEmail
    }
    private val preferences: SharedPreferences by lazy {
        requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }
    private fun saveUserToSharedPreferences(user: User) {
        with(preferences.edit()) {
            putString("user_name", user.name)
            putString("user_phone", user.phone)
            putString("user_email", user.email)
            apply()
        }
    }

}