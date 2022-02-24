package com.nexters.checkareer.domain.vo

import com.nexters.checkareer.domain.skill.Skill

data class SkillTree(
    val skill: Skill,
    val childSkills: List<Skill> = emptyList()
)
