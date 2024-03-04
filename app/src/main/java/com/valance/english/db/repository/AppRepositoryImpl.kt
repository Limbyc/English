package com.valance.english.db.repository

import com.valance.english.db.dao.CoursesDao
import com.valance.english.db.dao.QuestionDao
import com.valance.english.db.dao.TaskDao
import com.valance.english.db.dao.UserDao
import com.valance.english.db.entity.Courses
import com.valance.english.db.entity.Question
import com.valance.english.db.entity.Task
import com.valance.english.db.entity.User
import com.valance.english.domain.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val taskDao: TaskDao,
    private val coursesDao: CoursesDao,
    private val questionDao: QuestionDao
) : AppRepository {

    // Functions for User entity
    override suspend fun insertUser(user: User){
        return userDao.insertUser(user)
    }

    override suspend fun getUserById(userId: Int): User? {
        return userDao.getUserById(userId)
    }

    override suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }

    // Functions for Task entity
    override suspend fun getAllTasks(): List<Task> {
        return taskDao.getAllTasks()
    }

    override suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    // Functions for Courses entity
    override suspend fun getAllCourses(): List<Courses> {
        return coursesDao.getAllCourses()
    }

    // Functions for Question entity
    override suspend fun getQuestionsForTask(taskId: Int): List<Question> {
        return questionDao.getQuestionsForTask(taskId)
    }

}
