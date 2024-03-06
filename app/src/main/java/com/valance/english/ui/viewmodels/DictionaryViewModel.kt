package com.valance.english.ui.viewmodels

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valance.english.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(
    private val appRepository: AppRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val knownWordsKey = "known_words_count"

    private val _knownWordsCount = MutableLiveData<Int>()
    val knownWordsCount: LiveData<Int> get() = _knownWordsCount

    init {
        _knownWordsCount.value = sharedPreferences.getInt(knownWordsKey, 0)
    }

    fun sendIncrementEvent() {
        viewModelScope.launch {
            val currentCount = _knownWordsCount.value ?: 0
            _knownWordsCount.value = currentCount + 1
            Log.d("DictionaryViewModel", "Incrementing knownWordsCount: ${_knownWordsCount.value}")

            sharedPreferences.edit().putInt(knownWordsKey, _knownWordsCount.value ?: 0).apply()
        }
    }

    suspend fun getWordCount(): Int {
        return appRepository.getWordCount()
    }
}
