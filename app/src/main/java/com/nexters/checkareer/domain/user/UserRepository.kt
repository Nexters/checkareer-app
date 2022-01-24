package com.nexters.checkareer.domain.user

import com.nexters.checkareer.domain.util.Result

interface UserRepository {

    suspend fun findUser(forceUpdate: Boolean): Result<User>

    suspend fun findUsers(forceUpdate: Boolean): Result<List<User>>
}