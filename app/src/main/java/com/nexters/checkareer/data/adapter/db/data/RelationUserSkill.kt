package com.nexters.checkareer.data.adapter.db.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class RelationUserSkill(
    @Embedded val user: UserData,
    @Relation(
        parentColumn = "userId",
        entityColumn = "skillId",
        associateBy = Junction(UserSkillData::class)
    )
    private val skills: List<SkillData>
)
