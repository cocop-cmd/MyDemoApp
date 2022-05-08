package com.example.mydemoapp.ui.home

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydemoapp.R
import com.example.mydemoapp.data.LoginRepository
import com.example.mydemoapp.data.PostsRepository
import java.util.*

class HomeViewModel(
        private val loginRepository: LoginRepository,
        private val postRepository: PostsRepository
) : ViewModel() {

    private val _postForm = MutableLiveData<PostFormState>()
    val postFormState: LiveData<PostFormState> = _postForm

    private val _userResult = MutableLiveData<UserResult>()
    val userResult: LiveData<UserResult> = _userResult

    val posts = postRepository.posts

    fun logout() {
        // can be launched in a separate asynchronous job
        val success = loginRepository.logout()

        if (success) {
            _userResult.value = UserResult
        }
    }

    fun submitPost(
            description: String?,
            link: String
    ){
        if (description.isNullOrBlank()){
            _postForm.value = PostFormState(descriptionError = R.string.description_invalid)
            return
        } else if (!isUrl(link)){
            _postForm.value = PostFormState(linkError = R.string.link_invalid)
            return
        } else {
            val success = postRepository.savePost(description, link)

            if (success){
                _postForm.value = PostFormState(successMessage = R.string.insert_success)
            }
        }
    }

    fun deletePost(id: Long){
        postRepository.deletePost(id)
    }

    private fun isUrl(link: String): Boolean {
        return Patterns.WEB_URL.matcher(link.lowercase(Locale.getDefault())).matches();
    }
}