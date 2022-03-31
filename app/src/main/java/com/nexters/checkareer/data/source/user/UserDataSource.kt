package com.nexters.checkareer.data.source.user

import com.nexters.checkareer.data.adapter.db.data.UserProfile
import com.nexters.checkareer.domain.user.User
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.vo.Profile

interface UserDataSource {

    suspend fun findUser(): Result<User?>

    suspend fun findUserProfile(): Result<UserProfile?>

    suspend fun insertUserProfile(profile: Profile): Result<Unit>

    suspend fun deleteUserProfile(profile: Profile): Result<Unit>

    suspend fun updateUser(profile: Profile): Result<Unit>

    suspend fun findUserProfileByEmail(email: String): Result<Profile>

    suspend fun logout(user: User): Result<Unit>

}