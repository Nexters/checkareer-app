package com.nexters.checkareer.data.adapter.db.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nexters.checkareer.domain.skill.Skill


const val SKILL_TABLE = "skill"

@Entity(tableName = SKILL_TABLE)
data class SkillData(
    @PrimaryKey
    val skillId: String,
    val name: String,
    val parentId: Int? = null,
    val layer: Int = 3
) {
    fun toEntity(): Skill {
        return Skill(skillId, name, parentId, layer)
    }
}
