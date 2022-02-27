package com.nexters.checkareer.domain.vo

import android.os.Parcelable
import com.nexters.checkareer.domain.skill.Skill
import kotlinx.parcelize.Parcelize

@Parcelize
data class SkillTree(
    val skill: Skill,
    val childSkills: List<Skill> = emptyList()
): Parcelable
