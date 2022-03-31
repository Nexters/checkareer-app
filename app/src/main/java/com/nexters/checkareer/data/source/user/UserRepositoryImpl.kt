package com.nexters.checkareer.data.source.user

import com.nexters.checkareer.domain.error.UserNotFoundError
import com.nexters.checkareer.domain.user.User
import com.nexters.checkareer.domain.user.UserRepository
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.util.getValue
import com.nexters.checkareer.domain.vo.Profile
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val local: UserDataSource,
    private val remote: UserDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): UserRepository {

    override suspend fun findUser(): Result<User?> {
        return local.findUser()
    }

    override suspend fun findUserProfile(): Result<Profile> {

        return try {
            local.findUserProfile().getValue()?.let {
                Result.Success(it.toEntity())
            }?: run {
                Result.Error(UserNotFoundError())
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun insertUser(profile: Profile): Result<Unit> = withContext(ioDispatcher) {
        return@withContext try {
//            if (profile.user.isMember)
            remote.insertUserProfile(profile)

            local.insertUserProfile(profile)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun deleteUser(profile: Profile): Result<Unit> = withContext(ioDispatcher) {
        return@withContext try {
            local.deleteUserProfile(profile)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun updateUser(profile: Profile): Result<Unit> = withContext(ioDispatcher) {
        return@withContext try {
            if (profile.user.isMember)
                remote.updateUser(profile)
            local.updateUser(profile)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun findUserProfileByEmail(email: String): Result<Profile> {
        return try {
            val userProfile = findUserProfile().getValue()
            Result.Success(userProfile)
        } catch (e: Exception) {
            remote.findUserProfileByEmail(email)
        }
    }

    override suspend fun login(profile: Profile): Result<Unit> = withContext(ioDispatcher) {
        return@withContext try {
            if (profile.user.isMember)
                remote.insertUserProfile(profile)
            local.updateUser(profile)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun logout(user: User): Result<Unit> {
        return local.logout(user)
    }
}