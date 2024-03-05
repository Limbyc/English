package com.valance.english.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valance.english.db.entity.Question
import com.valance.english.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModel@Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> get() = _questions

    suspend fun getQuestionsForTask(taskId: Int) {
        val questionsForTask = appRepository.getQuestionsForTask(taskId)
        _questions.postValue(questionsForTask)
    }
}