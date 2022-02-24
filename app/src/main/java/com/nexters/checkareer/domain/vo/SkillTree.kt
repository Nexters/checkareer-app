package com.nexters.checkareer.domain.vo

data class SkillTree(
    val id: String,
    val name: String,
    val detailSkills: List<ChildSkill> = emptyList()
)

data class ChildSkill(
    val id: String,
    val name: String
)
