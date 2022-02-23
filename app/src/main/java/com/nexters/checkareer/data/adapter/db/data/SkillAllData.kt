package com.nexters.checkareer.data.adapter.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nexters.checkareer.domain.skill.Skill
import java.util.*


const val SKILL_ALL_TABLE = "skill_all"

@Entity(tableName = SKILL_ALL_TABLE)
data class SkillAllData(
    @PrimaryKey
    val skillId: String,
    val name: String,
    val parentId: Int? = null,
) {
    fun toEntity(): Skill {
        return Skill(skillId, name, parentId)
    }
}
