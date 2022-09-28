package com.gones.nearbymusic.data.repository

import com.gones.nearbymusic.domain.repository.UserRepository
import com.gones.nearbymusic.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val auth: FirebaseAuth
): UserRepository {
    override fun currentUser() = auth.currentUser

    override fun logout() = auth.signOut()

    override suspend fun login(email: String, password: String) = flow {
        try {
            emit(Resource.Loading())
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(authResult))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An error occured"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun register(email: String, password: String, name: String) = flow {
        try {
            emit(Resource.Loading())

            val authResult = auth.createUserWithEmailAndPassword(email, password).await()

            authResult.user!!.updateProfile(
                userProfileChangeRequest {
                    displayName = name
                }
            ).await()

            emit(Resource.Success(authResult))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An error occured"))
        }
    }.flowOn(Dispatchers.IO)
}