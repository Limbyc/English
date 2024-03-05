package com.valance.english.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Word")
data class Word(
    @PrimaryKey
    var id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String,
)
