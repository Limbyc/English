package com.valance.english.db.dao
import androidx.room.Dao
import androidx.room.Query
import com.valance.english.db.entity.Courses

@Dao
interface CoursesDao {
    @Query("SELECT * FROM Courses")
    suspend fun getAllCourses(): List<Courses>
}
