package com.valance.english.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.valance.english.db.entity.User
import com.valance.english.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

     suspend fun getUserById(userId: Int): User? {
        return appRepository.getUserById(userId)
    }
    suspend fun getMaxUserId(): Int?{
        return appRepository.getMaxUserId()
    }
}
