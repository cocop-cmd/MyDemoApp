package com.example.mydemoapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mydemoapp.data.LoginRepository
import com.example.mydemoapp.data.PostsRepository
import com.example.mydemoapp.database.AppDatabase

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class HomeViewModelFactory(private val appDatabase: AppDatabase) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                    loginRepository = LoginRepository(
                            dataSource = appDatabase
                    ),
                    postRepository = PostsRepository(
                            dataSource = appDatabase
                    )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}