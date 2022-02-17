package com.nexters.checkareer.domain.user

import com.nexters.checkareer.data.adapter.db.data.UserData
import com.nexters.checkareer.domain.vo.LogInInfo
import java.util.*

data class User(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val logInInfo: LogInInfo? = null
) {
    fun toUserData(): UserData {
        return UserData(
            userId = id,
            name = name,
            email = logInInfo?.email,
            photoUrl = logInInfo?.photoUrl
        )
    }
}
