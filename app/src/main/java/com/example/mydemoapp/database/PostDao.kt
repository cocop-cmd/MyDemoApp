package com.example.mydemoapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllItems(items : List<PostEntityItem>)

    @Query("SELECT * FROM PostEntityItem")
    fun getAllItems() : LiveData<List<PostEntityItem>>

    @Query("SELECT * FROM PostEntityItem WHERE id = :id")
    fun getItem(id: Long) : LiveData<PostEntityItem>

    @Query("DELETE FROM PostEntityItem")
    fun deleteEntries()

    @Delete
    fun deleteEntry(item: PostEntityItem)
}