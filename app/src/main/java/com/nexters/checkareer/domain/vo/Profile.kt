package com.nexters.checkareer.domain.vo

import com.nexters.checkareer.domain.category.Category
import com.nexters.checkareer.domain.user.User

data class Profile(
    val user: User,
    val category: List<Category>
)
