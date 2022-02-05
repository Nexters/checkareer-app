package com.nexters.checkareer.data.di

import android.content.Context
import androidx.room.Room
import com.nexters.checkareer.data.adapter.db.AppDataBase
import com.nexters.checkareer.data.adapter.db.dao.UserDao
import com.nexters.checkareer.data.source.category.local.CategoryLocal
import com.nexters.checkareer.data.source.category.local.CategoryLocalDataSource
import com.nexters.checkareer.data.source.category.local.CategoryRepositoryImpl
import com.nexters.checkareer.data.source.user.UserDataSource
import com.nexters.checkareer.data.source.user.UserRepositoryImpl
import com.nexters.checkareer.data.source.user.local.UserLocalDataSource
import com.nexters.checkareer.data.source.user.remote.UserRemoteDataSource
import com.nexters.checkareer.domain.category.CategoryRepository
import com.nexters.checkareer.domain.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LocalUserDataSource

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RemoteUserDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalCategoryDataSource

    @RemoteUserDataSource
    @Provides
    fun provideUserRemoteDataSource(): UserDataSource {
        return UserRemoteDataSource()
    }

    @LocalUserDataSource
    @Provides
    fun provideUserLocalDataSource(userDao: UserDao): UserDataSource {
        return UserLocalDataSource(userDao)
    }

    @Singleton


    
    @LocalCategoryDataSource
    @Provides
    fun provideCategoryLocalDataSource(): CategoryLocal {
        return CategoryLocalDataSource()
    }

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, AppDataBase.DB_NAME).build()

    @Singleton
    @Provides
    fun provideDao(database: AppDataBase) = database.userDao()


    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        @DataModule.LocalUserDataSource userLocalDataSource: UserDataSource,
        @DataModule.RemoteUserDataSource userRemoteDataSource: UserDataSource,
        ioDispatcher: CoroutineDispatcher
    ): UserRepository {
        return UserRepositoryImpl(
            local = userLocalDataSource, remote = userRemoteDataSource,  ioDispatcher
        )
    }

    @Singleton
    @Provides
    fun provideCategoryRepository(
        @DataModule.LocalCategoryDataSource local: CategoryLocal,
        ioDispatcher: CoroutineDispatcher
    ): CategoryRepository {
        return CategoryRepositoryImpl(
            local, ioDispatcher
        )
    }
}