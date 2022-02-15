package com.nexters.checkareer.data.source.user.remote

import com.nexters.checkareer.data.adapter.db.data.UserProfile
import com.nexters.checkareer.data.source.user.UserDataSource
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.vo.Profile
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor()
    : UserDataSource {

    override suspend fun findUserProfile(): Result<UserProfile> {
        TODO("Not yet implemented")
    }

    override suspend fun insertUserProfile(profile: Profile): Result<Unit> {
        TODO("Not yet implemented")
    }
}