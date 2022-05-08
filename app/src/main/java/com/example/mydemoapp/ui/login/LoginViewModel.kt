package com.example.mydemoapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydemoapp.data.LoginRepository

import com.example.mydemoapp.R

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    init {
        loginRepository.user.observeForever {
            if (it != null) {
                _loginResult.value = LoginResult(success = LoggedInUserView(displayName = it.username))
            }
        }
    }

    fun login(username: String) {
        // can be launched in a separate asynchronous job
        val id = loginRepository.login(username)

        if (id == null){
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }

    }

    fun loginDataChanged(username: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A validation for username
    private fun isUserNameValid(username: String?): Boolean {
        return with(username){
            when {
                isNullOrBlank() -> false
                length < 6 -> false
                else -> {
                    trim { it <= ' ' }.isNotEmpty()
                }
            }
        }
    }

}