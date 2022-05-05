package com.example.mydemoapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val ID = 101

@Entity
data class UserEntityItem(
        @PrimaryKey(autoGenerate = false)
        val id: Int = ID,
        @ColumnInfo(name = "first_name")
        val username: String,
)