package com.nexters.checkareer.data.di

import com.nexters.checkareer.data.adapter.db.dao.SkillDao
import com.nexters.checkareer.data.adapter.db.dao.UserDao
import com.nexters.checkareer.data.adapter.db.dao.UserSkillDao
import com.nexters.checkareer.data.network.ApiService
import com.nexters.checkareer.data.source.skill.local.SkillLocal
import com.nexters.checkareer.data.source.skill.local.SkillLocalDataSource
import com.nexters.checkareer.data.source.skill.SkillRepositoryImpl
import com.nexters.checkareer.data.source.skill.remote.SkillRemote
import com.nexters.checkareer.data.source.skill.remote.SkillRemoteDataSource
import com.nexters.checkareer.data.source.user.UserDataSource
import com.nexters.checkareer.data.source.user.UserRepositoryImpl
import com.nexters.checkareer.data.source.user.local.UserLocalDataSource
import com.nexters.checkareer.data.source.user.remote.UserRemoteDataSource
import com.nexters.checkareer.domain.skill.SkillRepository
import com.nexters.checkareer.domain.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RemoteCategoryDataSource

    @RemoteUserDataSource
    @Provides
    fun provideUserRemoteDataSource(): UserDataSource {
        return UserRemoteDataSource()
    }

    @LocalUserDataSource
    @Provides
    fun provideUserLocalDataSource(userDao: UserDao, userSkillDao: UserSkillDao): UserDataSource {
        return UserLocalDataSource(userDao, userSkillDao)
    }

    @RemoteCategoryDataSource
    @Provides
    fun provideCategoryRemoteDataSource(skillApi: ApiService): SkillRemote {
        return SkillRemoteDataSource(skillApi)
    }


    @LocalCategoryDataSource
    @Provides
    fun provideCategoryLocalDataSource(skillDao: SkillDao): SkillLocal {
        return SkillLocalDataSource(skillDao)
    }

    /*@Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME).build()

    @Singleton
    @Provides
    fun provideDao(database: AppDatabase) = database.userDao()*/


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
            local = userLocalDataSource, remote = userRemoteDataSource, ioDispatcher
        )
    }

    @Singleton
    @Provides
    fun provideCategoryRepository(
        @DataModule.LocalCategoryDataSource local: SkillLocal,
        @DataModule.RemoteCategoryDataSource remote: SkillRemote,
        ioDispatcher: CoroutineDispatcher
    ): SkillRepository {
        return SkillRepositoryImpl(
            local, remote, ioDispatcher
        )
    }
}