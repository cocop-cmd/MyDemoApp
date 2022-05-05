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
        loginRepository.login(username)

        // Todo: handle if no data was submitted
//        _loginResult.value = LoginResult(error = R.string.login_failed)
    }

    fun loginDataChanged(username: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String?): Boolean {
        return if (username == null) {
            false
        } else if (username.length < 6){
            false
        }else {
            username.trim { it <= ' ' }.isNotEmpty()
        }
    }
}