package com.nexters.checkareer.domain.user

import java.util.*

data class User(
    val id: String = UUID.randomUUID().toString(),
    val name: String
)
