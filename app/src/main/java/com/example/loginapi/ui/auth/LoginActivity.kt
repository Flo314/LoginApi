package com.example.loginapi.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.loginapi.R
import com.example.loginapi.databinding.ActivityLoginBinding
import com.example.loginapi.util.hide
import com.example.loginapi.util.show
import com.example.loginapi.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber

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
     ***********************************************************************************************
     */

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        // observer le loginResponse - l'observateur (Observer) renverra la valeur 'it'
        loginResponse.observe(this, Observer {
            progress_bar.hide()
            toast(it)
        })
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        toast(message)
    }
}
