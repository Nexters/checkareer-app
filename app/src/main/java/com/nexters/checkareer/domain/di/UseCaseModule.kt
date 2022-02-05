package com.nexters.checkareer.domain.di

import com.nexters.checkareer.domain.category.CategoryRepository
import com.nexters.checkareer.domain.usecase.GetProfileUseCase
import com.nexters.checkareer.domain.usecase.GetSkillCategoryUseCase
import com.nexters.checkareer.domain.usecase.SaveProfileUseCase
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
        userRepository: UserRepository,
        categoryRepository: CategoryRepository
    ): GetProfileUseCase {
        return GetProfileUseCase(userRepository, categoryRepository)
    }

    @Provides
    fun provideGetSkillCategoryUseCase(
        categoryRepository: CategoryRepository
    ): GetSkillCategoryUseCase {
        return GetSkillCategoryUseCase(categoryRepository)
    }

//    @Provides
//    fun provideSaveProfileUseCase(
//        userRepository: UserRepository,
//    ): SaveProfileUseCase {
//        return SaveProfileUseCase(userRepository)
//    }


}