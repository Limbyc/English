package com.valance.english.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.valance.english.db.entity.User
import com.valance.english.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    suspend fun insertUser(user: User){
        return appRepository.insertUser(user)
    }

    suspend fun getMaxUserId(): Int?{
        return appRepository.getMaxUserId()
    }
}