package com.example.loginapi.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.loginapi.data.db.entities.Comment

@Dao
interface CommentDao {

    // save les comments dans la db locale
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllComments(comments: List<Comment>)

    // récupérer tous les comments
    @Query("SELECT * from Comment")
    fun getComments() : LiveData<List<Comment>>

}