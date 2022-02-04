package com.nexters.checkareer.domain.category

import java.util.*

data class Category(
    val id: String = UUID.randomUUID().toString(),
    val name: String
)
