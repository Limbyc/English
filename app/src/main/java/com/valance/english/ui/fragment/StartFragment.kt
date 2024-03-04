package com.valance.english.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.valance.english.R
import com.valance.english.databinding.StartFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment: Fragment() {
    private  lateinit var binding: StartFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StartFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        StartButton()
    }

    private fun StartButton() {
        binding.startButton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_mainFragment)
        }
    }
}