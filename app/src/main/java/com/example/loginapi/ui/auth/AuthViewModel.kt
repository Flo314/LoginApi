package com.example.loginapi.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.loginapi.data.repositories.UserRepository

class AuthViewModel : ViewModel() {

    // variable qui seront utilisé pour obtenir (email, password) de l'interface utilisateur
    var email: String? = null
    var password: String? = null

    var authListenerVM: AuthListener? = null

    // Connection au clic
    fun onLoginButtonClick(view: View) {
        authListenerVM?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListenerVM?.onFailure("Invalid email or password")
            return
        }

        // c'est un livedata qu'on peut observer dans loginActivity
        /* problème car on crée une instance de UserRepository à l'intérieur de AuthViewModel
        et cela rend la classe UserRepository dépendante de AuthViewModel c'est une mauvaise pratique
        il vaut mieux l'injecter mais pour l'instant je fais comme çà*/
        val loginResponse = UserRepository().userLogin(email!!, password!!)
        // success on passe le livedata
        authListenerVM?.onSuccess(loginResponse)

    }
}