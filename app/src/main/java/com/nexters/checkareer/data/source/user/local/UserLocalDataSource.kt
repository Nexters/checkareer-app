package com.nexters.checkareer.data.source.user.local

import com.nexters.checkareer.data.adapter.db.dao.UserDao
import com.nexters.checkareer.data.adapter.db.dao.UserSkillDao
import com.nexters.checkareer.data.adapter.db.data.UserProfile
import com.nexters.checkareer.data.adapter.db.data.UserAndSkillData
import com.nexters.checkareer.data.source.user.UserDataSource
import com.nexters.checkareer.domain.error.DbError
import com.nexters.checkareer.domain.user.User
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.vo.Profile
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class UserLocalDataSource (
    private val userDao: UserDao,
    private val userSkillDao: UserSkillDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): UserDataSource {

    override suspend fun findUser(): Result<User?> = withContext(ioDispatcher)  {
        return@withContext try {
            val user = userDao.getUser()
            if (user.isEmpty()) {
                Result.Success(null)
            } else {
                Result.Success(user.first().toEntity())
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun findUserProfile(): Result<UserProfile?> = withContext(ioDispatcher) {
        return@withContext try {
            val profile = userDao.getUserProfile()
            Result.Success(profile)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun insertUserProfile(profile: Profile): Result<Unit> =
        withContext(ioDispatcher) {
            return@withContext try {
                profile.user.toUserData().let { user ->
                    userDao.insertUser(user)
                    userSkillDao.insertUserSkill(profile.skills.map {
                        UserAndSkillData(user.userId, it.id)
                    })
                    Result.Success(Unit)
                }
            } catch (e: Exception) {
                Result.Error(DbError(e.toString()))
            }
        }

    override suspend fun deleteUserProfile(profile: Profile): Result<Unit> =
        withContext(ioDispatcher) {
            return@withContext try {
                userDao.deleteUser(profile.user.id)
                userSkillDao.deleteUserSkill(listOf(profile.user.id))
                Result.Success(Unit)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun updateUser(profile: Profile): Result<Unit> =
        withContext(ioDispatcher) {
            return@withContext try {
                profile.user.toUserData().let { user ->
                    userDao.updateUser(user)
                    Result.Success(Unit)
                }
            } catch (e: Exception) {
                Result.Error(DbError(e.toString()))
            }
        }
}