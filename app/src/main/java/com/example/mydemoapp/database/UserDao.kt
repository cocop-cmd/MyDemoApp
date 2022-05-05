package com.example.mydemoapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveItem(item : UserEntityItem)

    @Query("SELECT * FROM UserEntityItem WHERE id = :id")
    fun getItem(id: Int) : LiveData<UserEntityItem>

    @Delete
    fun deleteEntry(item: UserEntityItem)
}