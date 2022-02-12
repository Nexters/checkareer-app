package com.nexters.checkareer.data.adapter.db.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class UserProfile(
    @Embedded val user: UserData,
    @Relation(
        parentColumn = "userId",
        entityColumn = "skillId",
        associateBy = Junction(UserSkillData::class)
    )
    val skills: List<SkillData>
)
