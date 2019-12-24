package com.example.loginapi.di

import android.app.Application
import com.example.loginapi.data.db.AppDatabase
import com.example.loginapi.data.network.MyApi
import com.example.loginapi.data.network.NetworkConnectionInterceptor
import com.example.loginapi.data.repositories.UserRepository
import com.example.loginapi.ui.auth.AuthViewModelFactory
import com.example.loginapi.ui.home.profile.ProfileViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * C'est une classe de base pour l'application qui sera instancié avant tout autre chose
 * Elle utilise Kodein pour générer les dépendances ou objets nécessaire
 * Elle implémente l'interface KodeinAware
 * Elle fournira tous les objets à l'activity
 */
class MVVMApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {

        import(androidXModule(this@MVVMApplication))
        // lier et instancier tous les objets
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
    }
}