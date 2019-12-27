package com.example.loginapi.ui.home.comments

import androidx.lifecycle.ViewModel
import com.example.loginapi.data.repositories.CommentsRepository
import com.example.loginapi.util.lazyDeffered

class CommentsViewModel(
    commentsRepository: CommentsRepository
) : ViewModel() {

    // obtenir les comments du repository
    val comments by lazyDeffered {
        commentsRepository.getComments()
    }

}
