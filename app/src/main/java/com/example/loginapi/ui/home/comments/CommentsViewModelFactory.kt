package com.example.loginapi.ui.home.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginapi.data.repositories.CommentsRepository
import com.example.loginapi.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class CommentsViewModelFactory(
    private val commentsRepository: CommentsRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CommentsViewModel(commentsRepository) as T
    }
}