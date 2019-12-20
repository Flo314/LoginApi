package com.example.loginapi.ui.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.loginapi.R
import com.example.loginapi.data.db.entities.User
import com.example.loginapi.databinding.ActivityLoginBinding
import com.example.loginapi.util.*
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

    override fun onSuccess(user: User?) {
        progress_bar.hide()
        //toast("${user?.firstname} - isConnected!")
        root_layout.snackbar("${user?.firstname} ${user?.lastname} - isConnected!")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        //toast(message)
        root_layout.snackbar(message)
    }

    // enregistrer le token
    fun registerJwt(data: String?){
        val sharedPreferences = getSharedPreferences("autorization", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("access_token", data)
        editor.apply()
    }

}
