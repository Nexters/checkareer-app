package com.nexters.checkareer.data.source.user

import com.nexters.checkareer.data.adapter.db.data.UserProfile
import com.nexters.checkareer.domain.user.User
import com.nexters.checkareer.domain.user.UserRepository
import com.nexters.checkareer.domain.util.Result
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

    override suspend fun findUser(forceUpdate: Boolean): Result<UserProfile> = withContext(ioDispatcher)  {
        return@withContext local.findUserProfile()
    }

    override suspend fun findUsers(forceUpdate: Boolean): Result<List<User>> = withContext(ioDispatcher)  {
        return@withContext Result.Success(emptyList())
    }

    override suspend fun insertUser(profile: Profile): Result<Unit> = withContext(ioDispatcher) {
        return@withContext local.insertUserProfile(profile)
    }

}