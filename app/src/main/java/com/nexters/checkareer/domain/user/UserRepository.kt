package com.nexters.checkareer.domain.user

import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.vo.Profile

interface UserRepository {

    suspend fun findUserProfile(forceUpdate: Boolean): Result<Profile>

    suspend fun insertUser(profile: Profile): Result<Unit>
}