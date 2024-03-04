package com.valance.english.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.valance.english.R
import com.valance.english.databinding.MainFragmentBinding
import com.valance.english.ui.adapter.TaskAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: Fragment() {

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoList = listOf(R.raw.video1, R.raw.video2, R.raw.video3)

//        val recyclerView: RecyclerView = binding.recyclerView
//        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        recyclerView.layoutManager = layoutManager
//        val videoAdapter = VideoAdapter(requireContext(), videoList)
//        recyclerView.adapter = videoAdapter

        val recyclerView: RecyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        val itemCount = 5
        val taskAdapter = TaskAdapter(requireContext(), itemCount)
        recyclerView.adapter = taskAdapter

        binding.order.setOnClickListener{
            findNavController().navigate(R.id.chooseCours)
        }
    }


}