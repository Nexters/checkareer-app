package com.nexters.checkareer.presentation.ui.login.model

import com.nexters.checkareer.domain.vo.LogInInfo

data class Account(
    val name: String?,
    val email: String?,
    val photoUrl: String?
){
    fun toDomain(): LogInInfo {
        return LogInInfo(
            name,
            email,
            photoUrl
        )
    }
}
