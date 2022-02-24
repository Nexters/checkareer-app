package com.nexters.checkareer.domain.skill

data class Skill(
    val id: String,
    val name: String,
    val parentId: Int? = null,
    val layer: Int = 3,
    val detailSkills: List<DetailSkill> = emptyList()
)

data class DetailSkill(
    val id: String,
    val name: String,
    val parentId: Int? = null,
    val layer: Int = 4
)
