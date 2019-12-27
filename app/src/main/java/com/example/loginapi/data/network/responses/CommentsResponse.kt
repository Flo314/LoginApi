package com.example.loginapi.data.network.responses

import com.example.loginapi.data.db.entities.Comment

data class CommentsResponse(
    val success: Boolean,
    val comments: List<Comment>
)