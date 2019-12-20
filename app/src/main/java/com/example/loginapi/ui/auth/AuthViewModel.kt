package com.example.loginapi.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.loginapi.data.repositories.UserRepository
import com.example.loginapi.util.Coroutines

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

        // on ne peux pas mettre toute les fonctions en suspend donc on utilise un CoroutinesScope
        Coroutines.main {
            val response = UserRepository().userLogin(email!!, password!!)
            // si j'ai une réponse cela veut dire que le user est connecté
            if (response.isSuccessful) {
                authListenerVM?.onSuccess(response.body()?.user!!)
            } else {
                authListenerVM?.onFailure("Error Code: ${response.code()}")
            }
        }


    }
}