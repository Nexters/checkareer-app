package com.nexters.checkareer.data.adapter.db.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.nexters.checkareer.domain.vo.Profile


data class UserProfile(
    @Embedded
    val user: UserData,

    @Relation(
        parentColumn = "userId",
        entity = SkillData::class,
        entityColumn = "skillId",
        associateBy = Junction(
            value = UserAndSkillData::class,
            parentColumn = "userId",
            entityColumn = "skillId"
        )
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

