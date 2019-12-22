package com.example.loginapi.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.loginapi.data.db.entities.CURRENT_USER_ID
import com.example.loginapi.data.db.entities.User

@Dao
interface UserDao {

    // insérer ou mettre à jour un user dans la db
    // return l'id d'insertion -> Long
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User): Long

    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getUser(): LiveData<User>
}