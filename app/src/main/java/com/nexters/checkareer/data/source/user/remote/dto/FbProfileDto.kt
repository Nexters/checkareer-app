package com.nexters.checkareer.data.source.user.remote.dto

import com.google.gson.annotations.SerializedName
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.user.User
import com.nexters.checkareer.domain.vo.LogInInfo
import com.nexters.checkareer.domain.vo.Profile
import com.nexters.checkareer.domain.vo.SkillTree

data class FbProfileDto (
    @SerializedName("user") val user: FbUserDto? = null,
    @SerializedName("skills") var skills: List<SkillTreeDto> = emptyList()
) {
    fun toDomain(): Profile {
        return Profile(
            User(
                user!!.id,
                user.name,
                true,
                LogInInfo(
                    user.email,
                    user.photoUrl
                )
            ),
            skills.map {
                SkillTree(
                    Skill(
                        it.skill!!.id,
                        it.skill.name,
                        it.skill.parentId,
                        it.skill.layer
                    ),
                    it.childSkills.map { child ->
                        Skill(
                            child.id,
                            child.name,
                            child.parentId,
                            child.layer
                        )
                    }
                )
            }
        )
    }
}

data class FbUserDto(
    @SerializedName("id") val id: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("email") val email: String? = null,
    @SerializedName("photoUrl") val photoUrl: String? = null
)

data class SkillTreeDto(
    @SerializedName("skill") val skill: SkillDto? = null,
    @SerializedName("childSkills") val childSkills: List<SkillDto> = emptyList()
)

data class SkillDto(
    @SerializedName("id") val id: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("parentId") val parentId: Int? = null,
    @SerializedName("layer") val layer: Int = 3
)