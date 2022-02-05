package com.nexters.checkareer.data.source.user.remote

import com.nexters.checkareer.data.adapter.db.data.UserProfileData
import com.nexters.checkareer.data.source.user.UserDataSource
import com.nexters.checkareer.domain.user.User
import com.nexters.checkareer.domain.util.Result
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor()
    : UserDataSource {

    override suspend fun findUser(): Result<UserProfileData> {
        TODO("Not yet implemented")
    }

    override suspend fun insertUserProfile(userProfileData: UserProfileData) {
        TODO("Not yet implemented")
    }
}