package com.example.loginapi.ui.auth

import com.example.loginapi.data.db.entities.User


/**
 * Afficher les messages d'erreur et de success au clic du bouton (méthode : onLoginButtonClick())
 * de AuthViewModel
 */
interface AuthListener {
    // utiliser quand on commence un appel à l'api pour la connection et afficher la progressbar
    fun onStarted()
    // authentification réussit
    fun onSuccess(data: User?)
    // authentification échouée
    fun onFailure(message: String)
}