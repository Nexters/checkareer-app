package com.nexters.checkareer.data.adapter.db.data

import androidx.room.Entity


const val USER_SKILL_TABLE = "user_and_skill"
@Entity(
    tableName = USER_SKILL_TABLE,
    primaryKeys = ["userId", "skillId"]
)
data class UserAndSkillData(
    val userId: String,
    val skillId: String,
//
//    @ColumnInfo(name = "createdAt", defaultValue = "CURRENT_TIMESTAMP")
//    val createdAt: Long = System.currentTimeMillis()
)
