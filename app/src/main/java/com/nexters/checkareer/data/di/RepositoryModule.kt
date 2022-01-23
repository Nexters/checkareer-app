//package com.nexters.checkareer.data.di
//
//import com.nexters.checkareer.data.source.category.local.CategoryLocalDataSource
//import com.nexters.checkareer.data.source.category.local.CategoryRepositoryImpl
//import com.nexters.checkareer.data.source.user.UserRepositoryImpl
//import com.nexters.checkareer.data.source.user.local.UserLocalDataSource
//import com.nexters.checkareer.data.source.user.remote.UserRemoteDataSource
//import com.nexters.checkareer.domain.category.CategoryRepository
//import com.nexters.checkareer.domain.user.UserRepository
//import dagger.Binds
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import kotlinx.coroutines.CoroutineDispatcher
//import javax.inject.Singleton
//
//
//@Module
//@InstallIn(SingletonComponent::class)
//object RepositoryModule {
//
//
//    @Provides
//    @Singleton
//    fun provideUserRepository(
//        @DataModule.LocalUserDataSource userLocalDataSource: UserLocalDataSource,
//        @DataModule.RemoteUserDataSource userRemoteDataSource: UserRemoteDataSource,
//        ioDispatcher: CoroutineDispatcher
//    ): UserRepository {
//        return UserRepositoryImpl(
//            userLocalDataSource, userRemoteDataSource, ioDispatcher
//        )
//    }
//
//
////    @Provides
////    @Singleton
////    fun provideCategoryRepository(
////        @DataModule.LocalCategoryDataSource categoryLocalDataSource: CategoryLocalDataSource,
////        ioDispatcher: CoroutineDispatcher
////    ): CategoryRepository {
////        return CategoryRepositoryImpl(
////            categoryLocalDataSource, ioDispatcher
////        )
////    }
//
//
//}
//
////@Module
////@InstallIn(SingletonComponent::class)
////abstract class TestRepositoryModule{
////    @Singleton
////    @Binds
////    abstract fun bindCategoryRepository(localDataSource: CategoryLocalDataSource): CategoryRepository
////}