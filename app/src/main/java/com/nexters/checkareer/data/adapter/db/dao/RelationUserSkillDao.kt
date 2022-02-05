package com.nexters.checkareer.data.adapter.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.nexters.checkareer.data.adapter.db.data.RelationUserSkill
import com.nexters.checkareer.data.adapter.db.data.USER_TABLE


@Dao
interface RelationUserSkillDao {

    @Transaction
    @Query("SELECT * FROM $USER_TABLE")
    fun getUserSkill(): List<RelationUserSkill>



}