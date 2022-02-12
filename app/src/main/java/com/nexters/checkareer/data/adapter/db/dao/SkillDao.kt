package com.nexters.checkareer.data.adapter.db.dao

import androidx.room.*
import com.nexters.checkareer.data.adapter.db.data.SkillData

@Dao
interface SkillDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSkill(skillData: List<SkillData>)

}