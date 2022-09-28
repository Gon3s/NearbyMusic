package com.gones.nearbymusic.domain.repository

import com.gones.nearbymusic.util.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun currentUser(): FirebaseUser?
    suspend fun login(email: String, password: String): Flow<Resource<AuthResult>>
    suspend fun register(email: String, password: String, name: String): Flow<Resource<AuthResult>>
    fun logout()
}