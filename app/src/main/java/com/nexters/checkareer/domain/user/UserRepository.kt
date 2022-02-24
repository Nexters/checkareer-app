package com.nexters.checkareer.domain.user

import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.vo.LogInInfo
import com.nexters.checkareer.domain.vo.Profile

interface UserRepository {

    suspend fun findUser(): Result<User?>

    suspend fun findUserProfile(): Result<Profile>

    suspend fun updateUser(profile: Profile): Result<Unit>

    suspend fun insertUser(profile: Profile): Result<Unit>

    suspend fun deleteUser(profile: Profile): Result<Unit>

}