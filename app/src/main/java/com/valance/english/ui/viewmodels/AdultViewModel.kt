package com.valance.english.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valance.english.db.entity.Courses
import com.valance.english.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdultViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    private val _courses: MutableLiveData<List<Courses>> = MutableLiveData()
    val courses: LiveData<List<Courses>> get() = _courses

    private val _filteredCourses = MutableLiveData<List<Courses>>()
    val filteredCourses: LiveData<List<Courses>> get() = _filteredCourses

    val currentFilterName = MutableLiveData<String?>()

    fun filterCoursesByTypeAndName(name: String?, currentFilterName: String?) {
        this.currentFilterName.value = name

        val filteredList = courses.value?.filter {
             (name == null || it.name.contains(name, true))
        } ?: emptyList()

        _filteredCourses.value = filteredList
    }
    fun getAllCourses() {
        viewModelScope.launch {
            val coursesList = appRepository.getAllCourses()
            _courses.value = coursesList
            Log.d("dadwada", coursesList.toString() )
        }
    }
}

