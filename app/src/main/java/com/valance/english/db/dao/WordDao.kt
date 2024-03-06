package com.valance.english.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.valance.english.db.entity.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM Word")
    suspend fun getAllWords(): List<Word>

    @Query("SELECT COUNT(*) FROM Word")
    suspend fun getWordCount(): Int
}