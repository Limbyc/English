package com.valance.english.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.valance.english.db.entity.Question

@Dao
interface QuestionDao {
    @Query("SELECT * FROM Question WHERE taskId = :taskId")
    suspend fun getQuestionsForTask(taskId: Int): List<Question>
}
