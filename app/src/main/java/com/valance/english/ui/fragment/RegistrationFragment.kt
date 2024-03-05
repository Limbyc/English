package com.valance.english.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.valance.english.R
import com.valance.english.databinding.RegistrationFragmentBinding
import com.valance.english.db.entity.User
import com.valance.english.ui.viewmodels.AdultViewModel
import com.valance.english.ui.viewmodels.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationFragment :Fragment() {

    private lateinit var binding: RegistrationFragmentBinding
    private val registrationViewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegistrationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registrationButton.setOnClickListener {
            val username = binding.name.text.toString()
            val phoneNumber = binding.phone.text.toString()
            val email = binding.email.text.toString()

            if (username.isNotBlank() && phoneNumber.isNotBlank() && email.isNotBlank()) {
                viewLifecycleOwner.lifecycleScope.launch {
                    try {
                        val maxId = registrationViewModel.getMaxUserId()?: 0

                        val user = User(maxId + 1, username, phoneNumber, email)

                        registrationViewModel.insertUser(user)
                        Log.d("dadada", maxId.toString())
                        findNavController().navigate(R.id.mainFragment)
                        Log.d("Registration", "success")
                    } catch (e: Exception) {
                        Log.d("Registration", "failed")
                        Log.e("Error", e.toString())
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }



        binding.appCompatImageView4.setOnClickListener{
            findNavController().navigate(R.id.chooseCours)
        }
    }
}
