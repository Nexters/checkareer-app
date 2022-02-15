package com.nexters.checkareer.data.adapter.db.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.nexters.checkareer.domain.user.User
import com.nexters.checkareer.domain.vo.Profile


data class UserProfile(
    @Embedded val user: UserData,
    @Relation(
        parentColumn = "userId",
        entityColumn = "skillId",
        associateBy = Junction(UserSkillData::class)
    )
    val skills: List<SkillData>
) {
    fun toEntity(): Profile {
        return Profile(
            user.toEntity(),
            skills.map { it.toEntity() }
        )
    }
}
