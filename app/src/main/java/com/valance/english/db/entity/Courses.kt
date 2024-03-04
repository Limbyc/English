package com.valance.english.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Courses")
data class Courses(
    @PrimaryKey
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "desc")
    var desc: String?,
    @ColumnInfo(name = "cost")
    var cost: Int
)
