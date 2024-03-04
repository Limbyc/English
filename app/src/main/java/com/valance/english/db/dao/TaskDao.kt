package com.valance.english.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.valance.english.db.entity.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    suspend fun getAllTasks(): List<Task>

    @Update
    suspend fun updateTask(task: Task)
}
