package com.example.mydemoapp.ui.login

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
        val starting: Unit? = null,
        val success: LoggedInUserView? = null,
        val error: Int? = null
)