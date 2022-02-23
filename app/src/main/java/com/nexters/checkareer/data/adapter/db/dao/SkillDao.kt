package com.nexters.checkareer.data.adapter.db.dao

import androidx.room.*
import com.nexters.checkareer.data.adapter.db.data.SkillData

@Dao
interface SkillDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSkills(skillData: List<SkillData>)

    @Query("DELETE from skill")
    suspend fun deleteUserSkill()

    @Query("SELECT * FROM skill" )
    fun getSkills(): List<SkillData>

}