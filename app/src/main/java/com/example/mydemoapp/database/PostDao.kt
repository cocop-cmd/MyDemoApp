package com.example.mydemoapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePost(items : PostEntityItem): Long?

    @Query("SELECT * FROM PostEntityItem")
    fun getAllItems() : LiveData<List<PostEntityItem>>

    @Query("SELECT * FROM PostEntityItem WHERE id = :id")
    fun getItem(id: Long) : LiveData<PostEntityItem>

    @Query("DELETE FROM PostEntityItem")
    fun deleteEntries()

    @Query("DELETE FROM PostEntityItem WHERE id = :postId")
    fun deleteByPostId(postId: Long): Int
}