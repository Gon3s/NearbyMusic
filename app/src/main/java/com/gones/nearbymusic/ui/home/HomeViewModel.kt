package com.gones.nearbymusic.ui.home

import androidx.lifecycle.ViewModel
import com.gones.nearbymusic.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    val user by lazy {
        userRepository.currentUser()
    }

    fun logout() {
        userRepository.logout()
    }
}