package com.gones.nearbymusic.di

import com.gones.nearbymusic.data.repository.UserRepositoryImpl
import com.gones.nearbymusic.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideUserRepository(auth: FirebaseAuth): UserRepository = UserRepositoryImpl(auth)
}