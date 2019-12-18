package com.example.loginapi

import android.app.Application
import timber.log.Timber

/**
 * Log accessible de partout
 * centralisé l'accès au repo pour avoir qu'une seule instance
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}