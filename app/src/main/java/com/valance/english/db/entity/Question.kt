package com.valance.english.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "Question")
data class Question(
    @PrimaryKey
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "translate")
    var translate: String,
    @ColumnInfo(name = "know")
    var know: Int?,
    @ColumnInfo(name = "dont_know")
    var dont_know: Int?,
    @ColumnInfo(name = "taskId")
    var taskId: Int
)




