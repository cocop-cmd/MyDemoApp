package com.example.mydemoapp.ui.home

/**
 * Authentication result : success (user details) or error message.
 */
data class PostResult(
        val starting: Unit? = null,
        val success: Unit? = null,
        val error: Int? = null
)