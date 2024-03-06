package com.valance.english.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.valance.english.R
import com.valance.english.databinding.ChoosecourseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseCours: Fragment(){

    private lateinit var binding: ChoosecourseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChoosecourseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener{
            findNavController().navigate(R.id.action_chooseCours_to_mainFragment)
        }
        binding.relativeLayout2.setOnClickListener{
            findNavController().navigate(R.id.action_chooseCours_to_adultCourse)
        }
        binding.relativeLayout3.setOnClickListener{
            findNavController().navigate(R.id.action_chooseCours_to_adultCourse)
        }
        binding.relativeLayout4.setOnClickListener{
            findNavController().navigate(R.id.action_chooseCours_to_registrationFragment)
        }
    }
}