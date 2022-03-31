package com.nexters.checkareer.domain.skill

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.nexters.checkareer.domain.vo.SkillTree
import kotlinx.parcelize.Parcelize

@Parcelize
data class Skill(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("parentId") val parentId: Int? = null,
    @SerializedName("layer") val layer: Int = 3
): Parcelable {
    fun toSkillTree(detailSkill: List<Skill>): SkillTree {
        return SkillTree(this, detailSkill)
    }

    fun toDetailSkills(): Skill {
        return Skill(id, name, parentId, layer)
    }
}