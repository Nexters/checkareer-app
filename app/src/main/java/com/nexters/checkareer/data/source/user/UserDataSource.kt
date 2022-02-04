package com.nexters.checkareer.data.source.user

import com.nexters.checkareer.domain.user.User
import com.nexters.checkareer.domain.util.Result

interface UserDataSource {
    suspend fun findUser(): Result<User>
}