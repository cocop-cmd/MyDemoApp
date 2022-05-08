package com.example.mydemoapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveItem(item : UserEntityItem): Long?

    @Query("SELECT * FROM UserEntityItem WHERE id = :id")
    fun getItem(id: Int) : LiveData<UserEntityItem>

    @Query("DELETE FROM UserEntityItem WHERE id = :userId")
    fun deleteByPostId(userId: Int): Int
}