package com.valance.english.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.valance.english.db.dao.CoursesDao
import com.valance.english.db.dao.QuestionDao
import com.valance.english.db.dao.TaskDao
import com.valance.english.db.dao.UserDao
import com.valance.english.db.dao.WordDao
import com.valance.english.db.entity.Courses
import com.valance.english.db.entity.Question
import com.valance.english.db.entity.Task
import com.valance.english.db.entity.User
import com.valance.english.db.entity.Word

@Database(entities = [Courses::class, Question::class, Task::class, User::class, Word::class],  version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun coursesDao(): CoursesDao
    abstract fun questionDao(): QuestionDao
    abstract fun taskDao(): TaskDao
    abstract fun userDao(): UserDao
    abstract fun wordDao(): WordDao

}