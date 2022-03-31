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
    fun provideSaveProfileUseCase(
        userRepository: UserRepository
    ): SaveProfileUseCase {
        return SaveProfileUseCase(userRepository)
    }

    @Provides
    fun provideDeleteProfileUseCase(
        userRepository: UserRepository
    ): LogoutUseCase {
        return LogoutUseCase(userRepository)
    }

    @Provides
    fun provideSignInUseCase(
        userRepository: UserRepository
    ): SignInUseCase {
        return SignInUseCase(userRepository)
    }

    @Provides
    fun provideGetAllSkillUseCase(
        skillRepository: SkillRepository
    ): GetAllSkillUseCase {
        return GetAllSkillUseCase(skillRepository)
    }

    @Provides
    fun provideGetSkillsByLayerUseCase(
        skillRepository: SkillRepository
    ): GetSkillsByLayerUseCase {
        return GetSkillsByLayerUseCase(skillRepository)
    }
}