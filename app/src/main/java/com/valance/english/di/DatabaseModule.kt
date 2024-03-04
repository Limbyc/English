package com.valance.english.di

import android.content.Context
import androidx.room.Room
import com.valance.english.db.AppDatabase
import com.valance.english.db.dao.CoursesDao
import com.valance.english.db.dao.QuestionDao
import com.valance.english.db.dao.TaskDao
import com.valance.english.db.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule{

    /*
            Db
    */
    @Provides
    fun provideCoursesDao(database: AppDatabase): CoursesDao {
        return database.coursesDao()
    }

    @Provides
    fun provideQuestionDao(database: AppDatabase): QuestionDao {
        return database.questionDao()
    }

    @Provides
    fun provideTaskDao(database: AppDatabase): TaskDao {
        return database.taskDao()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }



    @Provides
    @Singleton
    fun provideItemDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, "database.db"
        ).createFromAsset("data/database")
            .fallbackToDestructiveMigration()
            .build()
    }
}