package com.example.loginapi.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.loginapi.R
import com.example.loginapi.databinding.ActivityLoginBinding
import com.example.loginapi.util.toast

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // lier le layout à l'activity avec le dataBinding
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        // obtenir le viewModel
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        // définir le viewModel comme le viewModel de liaison (dataBinding)
        binding.viewmodel = viewModel
        // on définit le listener du viewModel comme celui de l'activity
        viewModel.authListenerVM = this
    }




    /**
     ***********************************************************************************************
     * Méthodes Interface AuthListener
     * **********************************************************************************************
     */

    override fun onStarted() {
        toast("Login started")
    }

    override fun onSuccess() {
        toast("Login success")
    }

    override fun onFailure(message: String) {
        toast(message)
    }
}
