package com.example.mydemoapp.data

import androidx.lifecycle.LiveData
import com.example.mydemoapp.database.AppDatabase
import com.example.mydemoapp.database.ID
import com.example.mydemoapp.database.UserEntityItem

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(dataSource: AppDatabase) {

    private val userDatabase = dataSource.getSimpleDao()

    // in-memory cache of the loggedInUser object
    val user: LiveData<UserEntityItem> = userDatabase.getItem(ID)

    fun logout(): Boolean {
        val rowsAffected = userDatabase.deleteByPostId(ID)
        return rowsAffected > 0
    }

    fun login(username: String): Long? {
        // save user in database
        val user = UserEntityItem(username = username)
        return userDatabase.saveItem(item = user)
    }

}