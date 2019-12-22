package com.example.loginapi.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.loginapi.data.repositories.UserRepository
import com.example.loginapi.util.ApiException
import com.example.loginapi.util.Coroutines

class AuthViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    // variable qui seront utilisé pour obtenir (email, password) de l'interface utilisateur
    var email: String? = null
    var password: String? = null

    var authListenerVM: AuthListener? = null

    // observer les changement du user dans la db locale
    fun getLoggedInUser() = userRepository.getUser()

    // Connection au clic
    fun onLoginButtonClick(view: View) {
        authListenerVM?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListenerVM?.onFailure("Invalid email or password")
            return
        }

        // on ne peux pas mettre toutes les fonctions en suspend donc on utilise un CoroutinesScope
        Coroutines.main {
            try {
                val authResponse = userRepository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListenerVM?.onSuccess(it)
                    // save user dans la db
                    userRepository.saveUserDb(it)
                    // return pour ne pas exécuter le onfailure()
                    return@main
                }
                // dans le cas ou le user est null
                authListenerVM?.onFailure(authResponse.message!!)
            }catch (e: ApiException) {
                authListenerVM?.onFailure(e.message!!)
            }
        }
    }
}