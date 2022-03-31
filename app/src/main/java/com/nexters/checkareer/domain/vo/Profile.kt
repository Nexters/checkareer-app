package com.nexters.checkareer.domain.vo

import com.nexters.checkareer.data.source.user.remote.dto.FbProfileDto
import com.nexters.checkareer.data.source.user.remote.dto.FbUserDto
import com.nexters.checkareer.data.source.user.remote.dto.SkillDto
import com.nexters.checkareer.data.source.user.remote.dto.SkillTreeDto
import com.nexters.checkareer.domain.user.User

data class Profile(
    val user: User,
    var skills: List<SkillTree> = emptyList()
) {
    fun toFbProfile(): FbProfileDto {
        return FbProfileDto(
            FbUserDto(
                user.id,
                user.name,
                user.logInInfo?.email,
                user.logInInfo?.photoUrl
            ),
            skills.map {
                SkillTreeDto(
                    SkillDto(
                        it.skill.id,
                        it.skill.name,
                        it.skill.parentId,
                        it.skill.layer
                    ),
                    it.childSkills.map { child ->
                        SkillDto(
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



