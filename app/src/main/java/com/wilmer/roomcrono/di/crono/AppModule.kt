package com.wilmer.roomcrono.di.crono

import android.content.Context
import androidx.room.Room
import com.wilmer.roomcrono.room.CronosDataBase
import com.wilmer.roomcrono.room.CronosDataBaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesCronosDao(cronosDataBase: CronosDataBase): CronosDataBaseDao {
        return cronosDataBase.cronoDao()
    }

    @Singleton
    @Provides
    fun providesCronosDatabase(@ApplicationContext context: Context): CronosDataBase {
        return Room.databaseBuilder(
            context,
            CronosDataBase::class.java,
            "cronos_db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}