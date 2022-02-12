package com.nexters.checkareer.data.adapter.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*


const val USER_SKILL_TABLE = "user_skill"


@Entity(
    tableName = USER_SKILL_TABLE,
    primaryKeys = ["userId", "skillId"]
)
data class UserSkillData(

    @ColumnInfo(name = "userId")
    val userId: String,

    @ColumnInfo(name = "skillId")
    val skillId: String,

    @ColumnInfo(name = "createdAt", defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: Long = System.currentTimeMillis()
)
