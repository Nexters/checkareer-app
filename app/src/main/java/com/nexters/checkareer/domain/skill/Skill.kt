package com.nexters.checkareer.domain.skill

import android.os.Parcelable
import com.nexters.checkareer.domain.vo.SkillTree
import kotlinx.parcelize.Parcelize

@Parcelize
data class Skill(
    val id: String,
    val name: String,
    val parentId: Int? = null,
    val layer: Int = 3
): Parcelable {
    fun toSkillTree(detailSkill: List<Skill>): SkillTree {
        return SkillTree(this, detailSkill)
    }

    fun toDetailSkills(): Skill {
        return Skill(id, name, parentId, layer)
    }
}