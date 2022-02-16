package com.nexters.checkareer.data.source.user

import com.nexters.checkareer.data.adapter.db.data.UserProfile
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.vo.Profile

interface UserDataSource {

    suspend fun findUserProfile(): Result<UserProfile>

    suspend fun insertUserProfile(profile: Profile): Result<Unit>

    suspend fun deleteUserProfile(profile: Profile): Result<Unit>

}