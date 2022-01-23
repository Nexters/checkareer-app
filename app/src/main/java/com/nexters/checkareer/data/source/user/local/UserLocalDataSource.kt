package com.nexters.checkareer.data.source.user.local

import com.nexters.checkareer.data.source.user.UserDataSource
import com.nexters.checkareer.domain.user.User
import com.nexters.checkareer.domain.util.Result
import javax.inject.Inject

class UserLocalDataSource (
//    private val userDao: UserDao
): UserDataSource {

    override suspend fun findUser(): Result<User> {
        return Result.Success(User("123", "checkareer"))
    }
}