package com.example.loginapi.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.loginapi.R
import com.example.loginapi.data.db.entities.User
import com.example.loginapi.databinding.ActivitySignupBinding
import com.example.loginapi.ui.home.HomeActivity
import com.example.loginapi.util.hide
import com.example.loginapi.util.show
import com.example.loginapi.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // lier le layout à l'activity avec le dataBinding
        val binding: ActivitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)

        // obtenir le viewModel
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        // définir le viewModel comme le viewModel de liaison (dataBinding)
        binding.viewmodel = viewModel
        // on définit le listener du viewModel comme celui de l'activity
        viewModel.authListenerVM = this

        // observe les changement si connection ok ouverture de l'activity Home
        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                // si il est connecté = authentification success on ouvre une autre activity
                Intent(this, HomeActivity::class.java).also {
                    // en cas de retour (press sur la flèche retour)
                    // l'activity se lancera sur HomeActivity évitant de revenir sur la connection
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
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
        //root_layout.snackbar("${user?.firstname} ${user?.lastname} - isConnected!")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        //toast(message)
        root_layout.snackbar(message)
    }
}
