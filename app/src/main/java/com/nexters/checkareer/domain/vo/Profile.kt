package com.nexters.checkareer.domain.vo

import com.nexters.checkareer.domain.user.User

data class Profile(
    val user: User,
    var skills: List<SkillTree> = emptyList()
)


