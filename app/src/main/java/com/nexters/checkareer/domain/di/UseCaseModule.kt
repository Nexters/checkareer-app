package com.nexters.checkareer.domain.di

import com.nexters.checkareer.domain.skill.SkillRepository
import com.nexters.checkareer.domain.usecase.*
import com.nexters.checkareer.domain.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetProfileUseCase(
        userRepository: UserRepository
    ): GetProfileUseCase {
        return GetProfileUseCase(userRepository)
    }

    @Provides
    fun provideSyncSkillsUseCase(
        skillRepository: SkillRepository
    ): SyncSkillsUseCase {
        return SyncSkillsUseCase(skillRepository)
    }

    @Provides
    fun provideGetUserUseCase(
        userRepository: UserRepository
    ): GetUserUseCase {
        return GetUserUseCase(userRepository)
    }

    @Provides
    fun provideGetSkillsUseCase(
        skillRepository: SkillRepository
    ): GetSkillsUseCase {
        return GetSkillsUseCase(skillRepository)
    }

    @Provides
    fun provideSaveProfileUseCase(
        userRepository: UserRepository,
        skillRepository: SkillRepository
    ): SaveProfileUseCase {
        return SaveProfileUseCase(userRepository, skillRepository)
    }

    @Provides
    fun provideDeleteProfileUseCase(
        userRepository: UserRepository,
        skillRepository: SkillRepository
    ): DeleteProfileUseCase {
        return DeleteProfileUseCase(userRepository, skillRepository)
    }

    @Provides
    fun provideSignInUseCase(
        userRepository: UserRepository
    ): SignInUseCase {
        return SignInUseCase(userRepository)
    }
}