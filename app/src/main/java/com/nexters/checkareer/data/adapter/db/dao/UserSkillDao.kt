package com.nexters.checkareer.data.adapter.db.dao

import androidx.room.*
import com.nexters.checkareer.data.adapter.db.data.UserAndSkillData

@Dao
interface UserSkillDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserSkill(userSkillData: List<UserAndSkillData>)

}