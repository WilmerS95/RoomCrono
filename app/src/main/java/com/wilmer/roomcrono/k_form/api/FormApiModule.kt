package com.wilmer.roomcrono.k_form.api

import com.wilmer.roomcrono.di.qualifiers.FormRetrofit
import com.wilmer.roomcrono.repositories.form.FormRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FormApiModule {

    @FormRetrofit
    @Provides
    @Singleton
    fun providesFormRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://6695f1200312447373c07d56.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(@FormRetrofit retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideFormRepository(apiService: ApiService): FormRepository {
        return FormRepository(apiService)
    }
}