package com.nexters.checkareer.data.source.user

import com.nexters.checkareer.data.adapter.db.data.UserProfileData
import com.nexters.checkareer.domain.user.User
import com.nexters.checkareer.domain.user.UserRepository
import com.nexters.checkareer.domain.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val local: UserDataSource,
    private val remote: UserDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): UserRepository {

    override suspend fun findUser(forceUpdate: Boolean): Result<UserProfileData> {
        return local.findUser()
    }

    override suspend fun findUsers(forceUpdate: Boolean): Result<List<User>> {
        return Result.Success(emptyList())
    }

    override suspend fun insertUser(userProfileData: UserProfileData) = withContext(ioDispatcher) {
        local.insertUserProfile(userProfileData)
    }


}