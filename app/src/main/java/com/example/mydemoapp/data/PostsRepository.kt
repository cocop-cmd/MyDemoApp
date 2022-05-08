package com.example.mydemoapp.data

import androidx.lifecycle.LiveData
import com.example.mydemoapp.database.AppDatabase
import com.example.mydemoapp.database.ID
import com.example.mydemoapp.database.PostEntityItem

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class PostsRepository(dataSource: AppDatabase) {

    private val postDatabase = dataSource.getPostDao()
    private val userDatabase = dataSource.getSimpleDao()

    lateinit var username: String

    init {
        userDatabase.getItem(ID).observeForever {
            if (it != null) {
                username = it.username
            }
        }
    }

    // in-memory cache of the loggedInUser object
    val posts: LiveData<List<PostEntityItem>> = postDatabase.getAllItems()

    fun savePost(
            description: String,
            link: String
    ): Boolean {
        val post = PostEntityItem(
                username = username,
                description = description,
                link = link
        )
        return postDatabase.savePost(post) != null
    }

    fun deletePost(id: Long): Boolean {
        return postDatabase.deleteByPostId(id) > 0
    }

}