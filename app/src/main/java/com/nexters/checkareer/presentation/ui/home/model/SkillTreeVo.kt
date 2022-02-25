package com.nexters.checkareer.presentation.ui.home.model

data class SkillTreeVo(
    val skill: SkillVo,
    val childSkill: List<SkillVo>
)
