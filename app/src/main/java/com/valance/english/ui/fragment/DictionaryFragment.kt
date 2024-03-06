package com.valance.english.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.valance.english.R
import com.valance.english.databinding.DictionaryFragmentBinding
import com.valance.english.ui.viewmodels.DictionaryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DictionaryFragment : Fragment() {

    private lateinit var binding: DictionaryFragmentBinding
    private val dictionaryViewModel: DictionaryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DictionaryFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.appCompatImageView7.setOnClickListener{
            findNavController().navigate(R.id.action_dictionaryFragment_to_mainFragment)
        }
        lifecycleScope.launch {
            val wordCount = dictionaryViewModel.getWordCount()
            binding.allWords.text = "$wordCount"
        }

        lifecycleScope.launchWhenResumed {
            val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val knowWord = sharedPreferences.getInt("known_words_count", 0)
            binding.znay.text = "$knowWord выученных слов"
            binding.wordZapas.text = "$knowWord"
            updateTextViewForWordCount(knowWord)
        }


    }

    private fun updateTextViewForWordCount(knowWord: Int) {
        val newText = when {
            knowWord == 0 -> "Скорлупчатый"
            knowWord in 1..9 -> "Скорлупчатый"
            knowWord in 10..19 -> "Любознательный"
            knowWord in 20..29 -> "Вылупившийся"
            knowWord in 30..39 -> "Малыш"
            knowWord in 40..49 -> "Школьник"
            knowWord in 50..Int.MAX_VALUE -> "Студент"
            else -> ""
        }
        binding.zvanya.text = newText
    }

}
