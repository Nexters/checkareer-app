package com.nexters.checkareer.data.source.user.local

import com.nexters.checkareer.data.adapter.db.dao.UserDao
import com.nexters.checkareer.data.adapter.db.dao.UserSkillDao
import com.nexters.checkareer.data.adapter.db.data.UserData
import com.nexters.checkareer.data.adapter.db.data.UserProfile
import com.nexters.checkareer.data.adapter.db.data.UserSkillData
import com.nexters.checkareer.data.source.user.UserDataSource
import com.nexters.checkareer.domain.error.DbError
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

    override suspend fun findUserProfile(): Result<UserProfile> {
        return Result.Success(userDao.getUserSkill())
    }

    override suspend fun insertUserProfile(profile: Profile): Result<Unit> = withContext(ioDispatcher) {
        return@withContext try {
            userDao.insertUser(UserData(name = profile.user.name) )
            userSkillDao.insertUserSkill(profile.skills.map {
                UserSkillData(profile.user.id, it.id)
            })
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DbError(e.toString()))
        }
    }
}