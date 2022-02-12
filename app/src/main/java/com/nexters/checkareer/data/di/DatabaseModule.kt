package com.nexters.checkareer.data.di

import android.content.Context
import androidx.room.Room
import com.nexters.checkareer.data.adapter.db.AppDatabase
import com.nexters.checkareer.data.adapter.db.dao.SkillDao
import com.nexters.checkareer.data.adapter.db.dao.UserDao
import com.nexters.checkareer.data.adapter.db.dao.UserSkillDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "checkareer.db"
        ).build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideSkillDao(database: AppDatabase): SkillDao {
        return database.skillDao()
    }

    @Provides
    fun provideUserSkillDao(database: AppDatabase): UserSkillDao {
        return database.userSkillDao()
    }
}