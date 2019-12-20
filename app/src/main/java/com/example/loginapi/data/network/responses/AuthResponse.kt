package com.example.loginapi.data.network.responses

import com.example.loginapi.data.db.entities.User

/**
 * Mapper la response JSON en classe Kotlin
 * Définir chaque attributs de l'objet JSON
 */
data class AuthResponse(
   val success: Boolean?,
   val data: String?,
   val id: Int?,
   val user: User?,
   val token: String?
)
