package com.nexters.checkareer.domain.skill

import com.nexters.checkareer.domain.vo.ChildSkill
import com.nexters.checkareer.domain.vo.SkillTree

data class Skill(
    val id: String,
    val name: String,
    val parentId: Int? = null,
    val layer: Int = 3
) {
    fun toSkillTree(detailSkill: List<ChildSkill>): SkillTree {
        return SkillTree(id, name, detailSkill)
    }

    fun toDetailSkills(): ChildSkill {
        return ChildSkill(id, name)
    }
}