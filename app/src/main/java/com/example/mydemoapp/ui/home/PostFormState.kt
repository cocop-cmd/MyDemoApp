package com.example.mydemoapp.ui.home

/**
 * Data validation state of the login form.
 */
data class PostFormState(val descriptionError: Int? = null,
                         val linkError: Int? = null,
                         val successMessage: Int? = null)