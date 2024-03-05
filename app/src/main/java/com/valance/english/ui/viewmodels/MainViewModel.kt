package com.valance.english.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.valance.english.db.entity.Task
import com.valance.english.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(private val appRepository: AppRepository) : ViewModel() {
     suspend fun getAllTasks(): List<Task> {
        return appRepository.getAllTasks()
    }
}