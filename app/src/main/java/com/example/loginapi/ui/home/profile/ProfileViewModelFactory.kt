package com.example.loginapi.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginapi.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(userRepository) as T
    }
}