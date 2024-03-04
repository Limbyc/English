package com.valance.english.domain

import com.valance.english.db.entity.Courses
import com.valance.english.db.entity.Question
import com.valance.english.db.entity.Task
import com.valance.english.db.entity.User

interface AppRepository {

    // Functions for User entity
    suspend fun insertUser(user: User): Unit
    suspend fun getUserById(userId: Int): User?
    suspend fun getAllUsers(): List<User>

    // Functions for Task entity
    suspend fun getAllTasks(): List<Task>
    suspend fun updateTask(task: Task)

    // Functions for Courses entity
    suspend fun getAllCourses(): List<Courses>

    // Functions for Question entity
    suspend fun getQuestionsForTask(taskId: Int): List<Question>
}
