package com.nexters.checkareer.data.entity

import com.google.gson.annotations.SerializedName
import com.nexters.checkareer.domain.skill.Skill

data class SkillEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("parentid") val parentId: Int?,
    @SerializedName("namekr") val nameKr: String,
    @SerializedName("nameen") val nameEn: String,
    @SerializedName("type") val type: Int
) {

    fun toEntity(): Skill {
        return Skill(id.toString(), nameKr, parentId)
    }

}
