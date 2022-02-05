package com.nexters.checkareer.domain.user

import com.nexters.checkareer.data.adapter.db.data.UserProfileData
import com.nexters.checkareer.domain.util.Result

interface UserRepository {

    suspend fun findUser(forceUpdate: Boolean): Result<UserProfileData>

    suspend fun findUsers(forceUpdate: Boolean): Result<List<User>>

    suspend fun insertUser(userProfileData: UserProfileData)
}