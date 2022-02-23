package com.nexters.checkareer.data.adapter.db.dao

import androidx.room.*
import com.nexters.checkareer.data.adapter.db.data.SkillAllData
import com.nexters.checkareer.data.adapter.db.data.SkillData
import com.nexters.checkareer.data.adapter.db.data.UserProfile

@Dao
interface SkillDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSkills(skillData: List<SkillData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllSkills(skillData: List<SkillAllData>)

    @Query("SELECT * FROM skill_all" )
    fun getAllSkills(): List<SkillAllData>

}