package com.nexters.checkareer.data.adapter.db.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.nexters.checkareer.domain.vo.Profile
import com.nexters.checkareer.domain.vo.SkillTree
import timber.log.Timber


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
        val parentSkills = skills.filter { it.layer == 3 }
        val detailSkills = skills.filter { it.layer == 4 }
        Timber.i("parentSkills : $parentSkills")
        Timber.i("detailSkills : $detailSkills")

        val results = parentSkills.map { parent -> parent.toEntity().toSkillTree(detailSkills.filter { it.parentId == parent.skillId.toInt() }.map { it.toEntity().toDetailSkills() })  }
        return Profile(
            user.toEntity(),
            results
        )
    }
}

