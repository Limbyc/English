package com.valance.english.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.valance.english.db.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User WHERE id = :userId")
    suspend fun getUserById(userId: Int): User?

    @Query("SELECT * FROM User")
    suspend fun getAllUsers(): List<User>
}
