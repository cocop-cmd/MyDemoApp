package com.example.mydemoapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostEntityItem(
        @PrimaryKey(autoGenerate = true)
        val id: Long? = null,
        @ColumnInfo(name = "first_name")
        val username: String,
        @ColumnInfo(name = "description")
        val description: String,
        @ColumnInfo(name = "link")
        val link: String
)