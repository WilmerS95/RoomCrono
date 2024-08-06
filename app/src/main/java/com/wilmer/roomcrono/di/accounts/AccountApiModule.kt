package com.wilmer.roomcrono.di.accounts

import com.wilmer.roomcrono.di.qualifiers.AccountRetrofit
import com.wilmer.roomcrono.services.account.AccountApiService
import com.wilmer.roomcrono.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AccountApiModule {

    @AccountRetrofit
    @Provides
    @Singleton
    fun providesAccountRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesApiAccounts(@AccountRetrofit retrofit: Retrofit): AccountApiService {
        return retrofit.create(AccountApiService::class.java)
    }
}