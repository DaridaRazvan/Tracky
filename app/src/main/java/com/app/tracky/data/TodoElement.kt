package com.app.tracky.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todoElement")
data class TodoElement(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    var isChecked: Boolean
)