package com.valance.english.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.valance.english.R
import com.valance.english.databinding.TaskFragmentBinding
import com.valance.english.db.entity.Question
import com.valance.english.ui.viewmodels.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TaskFragment : Fragment() {

    private lateinit var binding: TaskFragmentBinding
    private val viewModel: TaskViewModel by viewModels()
    private val totalQuestions = 9
    private var currentQuestionIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TaskFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedTaskId = arguments?.getInt("selectedTaskId", -1)

        if (selectedTaskId != -1) {
            observeQuestions()
            binding.buttonNextTask.setOnClickListener {
                displayNextQuestion()
            }
            if (selectedTaskId != null) {
                loadQuestionsForTask(selectedTaskId)
            }
        } else {
            Log.e("TaskFragment", "Selected taskId is invalid.")
        }

        binding.appCompatImageView5.setOnClickListener{
            showExitConfirmationDialog()
        }
    }

    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Предупреждение")
        builder.setMessage("Вы уверены, что хотите покинуть задание? Ваши текущие результаты могут быть потеряны.")

        builder.setPositiveButton("Да") { _, _ ->
            findNavController().popBackStack()
        }

        builder.setNegativeButton("Нет") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
    private fun observeQuestions() {
        viewModel.questions.observe(viewLifecycleOwner, Observer { questions ->
            if (questions.isNotEmpty()) {
                displayQuestion(questions[currentQuestionIndex])
            }
        })
    }

    private fun loadQuestionsForTask(taskId: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getQuestionsForTask(taskId)
        }
    }

    private fun displayNextQuestion() {
        currentQuestionIndex++

        val progress = (currentQuestionIndex.toFloat() / totalQuestions.toFloat() * 100).toInt()

        binding.progressBar.progress = progress
        val questions = viewModel.questions.value

        if (questions != null && currentQuestionIndex < questions.size) {
            displayQuestion(questions[currentQuestionIndex])
        } else {
            findNavController().navigate(R.id.mainFragment)
        }
    }

    private fun displayQuestion(question: Question) {
        binding.nameWord.text = question.name
        binding.translate.text = question.translate
    }
}

