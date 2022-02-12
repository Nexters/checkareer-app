package com.nexters.checkareer.data.adapter.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


const val SKILL_TABLE = "skill"

@Entity(tableName = SKILL_TABLE)
data class SkillData(
    @PrimaryKey
    @ColumnInfo(name = "skillId")
    val skillId: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "parentId")
    val parentId: String? = null,
)
