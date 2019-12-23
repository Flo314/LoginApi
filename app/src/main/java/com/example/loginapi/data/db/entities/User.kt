package com.example.loginapi.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

// on souhaite stocker que le user authentifié pas plusieurs users
// pas besoin de faire l'incrémentation automatique de l'id
const val CURRENT_USER_ID = 0

@Entity
data class User(
    var id: Int? = null,
    var firstname: String? = null,
    var lastname: String? = null,
    var email: String? = null


){
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}
