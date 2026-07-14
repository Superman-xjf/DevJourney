package com.xjf.devjourney.core.database.di

import android.content.Context
import androidx.room.Room
import com.xjf.devjourney.core.database.DevJourneyDatabase
import com.xjf.devjourney.core.database.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): DevJourneyDatabase {
        return Room.databaseBuilder(
            context,
            DevJourneyDatabase::class.java,
            "devjourney.db",
        ).build()
    }

    @Provides
    fun provideTaskDao(database: DevJourneyDatabase): TaskDao {
        return database.taskDao()
    }
}