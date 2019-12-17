package com.example.loginapi.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel

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
        // success
        authListenerVM?.onSuccess()

    }
}