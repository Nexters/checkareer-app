package com.nexters.checkareer.data.source.user.local

import com.nexters.checkareer.data.adapter.db.dao.RelationUserSkillDao
import com.nexters.checkareer.data.adapter.db.dao.UserDao
import com.nexters.checkareer.data.adapter.db.data.UserProfileData
import com.nexters.checkareer.data.source.user.UserDataSource
import com.nexters.checkareer.domain.util.Result

class UserLocalDataSource (
    private val userDao: UserDao,
    private val relationUserSkillDao: RelationUserSkillDao
): UserDataSource {

    override suspend fun findUser(): Result<UserProfileData> {
        return Result.Success(userDao.getUserProfile())
    }

    override suspend fun insertUserProfile(userProfileData: UserProfileData) {
        val result = userDao.insertUserProfile(userProfileData)
        println(result)
    }
}