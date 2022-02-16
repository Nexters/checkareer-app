package com.nexters.checkareer.data.source.user.local

import com.nexters.checkareer.data.adapter.db.dao.UserDao
import com.nexters.checkareer.data.adapter.db.dao.UserSkillDao
import com.nexters.checkareer.data.adapter.db.data.UserData
import com.nexters.checkareer.data.adapter.db.data.UserProfile
import com.nexters.checkareer.data.adapter.db.data.UserAndSkillData
import com.nexters.checkareer.data.source.user.UserDataSource
import com.nexters.checkareer.domain.error.DbError
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.vo.Profile
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception

class UserLocalDataSource (
    private val userDao: UserDao,
    private val userSkillDao: UserSkillDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): UserDataSource {

    override suspend fun findUserProfile(): Result<UserProfile> = withContext(ioDispatcher) {
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
                UserData(
                    userId = profile.user.id,
                    name = profile.user.name
                ).let { user ->
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
                UserData(
                    userId = profile.user.id,
                    name = profile.user.name
                ).let { user ->
                    userDao.deleteUser(user.userId)
                    userSkillDao.deleteUserSkill(profile.skills.map {
                        user.userId
                    })
                    Result.Success(Unit)
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

}