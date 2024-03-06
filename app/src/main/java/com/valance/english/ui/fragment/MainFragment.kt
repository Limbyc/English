package com.valance.english.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.valance.english.R
import com.valance.english.databinding.MainFragmentBinding
import com.valance.english.ui.adapter.TaskAdapter
import com.valance.english.ui.adapter.VideoAdapter
import com.valance.english.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment: Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var taskAdapter: TaskAdapter
    private val mainViewModel: MainViewModel by viewModels()

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

        setupVideoRecyclerView()

        val recyclerView: RecyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        taskAdapter = TaskAdapter(requireContext())
        binding.recyclerView.adapter = taskAdapter

        lifecycleScope.launch {
            val tasks = mainViewModel.getAllTasks()
            taskAdapter.setTasks(tasks)
            Log.d("Список заданий", tasks.toString())
        }

        taskAdapter.setOnItemClickListener(object : TaskAdapter.OnItemClickListener {
            override fun onItemClick(taskId: Int) {
                val bundle = Bundle().apply {
                    putInt("selectedTaskId", taskId)
                }
                findNavController().navigate(R.id.action_mainFragment_to_taskFragment, bundle)
            }
        })

        binding.order.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_chooseCours)
        }

        binding.slovar.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_dictionaryFragment)
        }
    }


    private fun setupVideoRecyclerView() {
        val videoList = listOf((R.raw.video1), (R.raw.video2), (R.raw.video3))
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val videoAdapter = VideoAdapter(videoList, object : VideoAdapter.OnItemClickListener {
            override fun onItemClick(videoId: Int) {
                val bundle = Bundle().apply {
                    putInt("selectedVideoId", videoId)
                }
                findNavController().navigate(R.id.action_mainFragment_to_videoFragment, bundle)
            }
        })

        binding.recyclerView1.apply {
            setLayoutManager(layoutManager)
            adapter = videoAdapter
        }
    }



}