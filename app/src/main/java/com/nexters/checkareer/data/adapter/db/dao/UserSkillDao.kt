package com.nexters.checkareer.data.adapter.db.dao

import androidx.room.*
import com.nexters.checkareer.data.adapter.db.data.UserAndSkillData

@Dao
interface UserSkillDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserSkill(userSkillData: List<UserAndSkillData>)

    @Query("DELETE from user_and_skill where userId in (:userIds)")
    suspend fun deleteUserSkill(userIds: List<String>)

}