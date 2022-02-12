package com.nexters.checkareer.domain.user

import com.nexters.checkareer.data.adapter.db.data.UserProfile
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.vo.Profile

interface UserRepository {

    suspend fun findUser(forceUpdate: Boolean): Result<UserProfile>

    suspend fun findUsers(forceUpdate: Boolean): Result<List<User>>

    suspend fun insertUser(profile: Profile): Result<Unit>
}