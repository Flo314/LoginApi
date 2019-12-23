package com.example.loginapi.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.loginapi.data.network.NetworkConnectionInterceptor
import com.example.loginapi.data.repositories.UserRepository
import com.example.loginapi.util.ApiException
import com.example.loginapi.util.Coroutines
import com.example.loginapi.util.NoInternetException

class AuthViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    var firstname: String? = null
    var lastname: String? = null
    var passwordConfirm: String? = null

    // variable qui seront utilisé pour obtenir (email, password) de l'interface utilisateur
    var email: String? = null
    var password: String? = null

    var authListenerVM: AuthListener? = null

    // observer les changement du user dans la db locale
    fun getLoggedInUser() = userRepository.getUser()

    // Connection au clic SIGN IN
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
            }catch (e: NoInternetException) {
                authListenerVM?.onFailure(e.message!!)
            }
        }
    }

    // register here
    fun onSignup(view: View) {
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    // si déjà un compte alors qu'il est sur la page d'inscription
    fun onLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    // Connection au clic SIGN UP
    fun onSignupButtonClick(view: View) {
        authListenerVM?.onStarted()
        if (firstname.isNullOrEmpty() && lastname.isNullOrEmpty()) {
            authListenerVM?.onFailure("Firstname and Lastname is required")
            return
        }
        if (email.isNullOrEmpty()) {
            authListenerVM?.onFailure("Email is required")
            return
        }
        if (password.isNullOrEmpty()) {
            authListenerVM?.onFailure("Please enter a password")
            return
        }
        if (password != passwordConfirm) {
            authListenerVM?.onFailure("Password did not match")
            return
        }

        // on ne peux pas mettre toutes les fonctions en suspend donc on utilise un CoroutinesScope
        Coroutines.main {
            try {
                val authResponse = userRepository.userSignup(firstname!!,lastname!!,email!!, password!!)
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
            }catch (e: NoInternetException) {
                authListenerVM?.onFailure(e.message!!)
            }
        }
    }
}