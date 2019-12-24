package com.example.loginapi.ui.home.profile

import androidx.lifecycle.ViewModel
import com.example.loginapi.data.repositories.UserRepository

class ProfileViewModel(
    userRepository: UserRepository
) : ViewModel() {

    // retourne un liveData qu'on peut lier avec le layout
    val user = userRepository.getUser()

}
