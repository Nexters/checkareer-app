package com.nexters.checkareer.data.adapter.db.dao

import androidx.room.*
import com.nexters.checkareer.data.adapter.db.data.UserProfile
import com.nexters.checkareer.data.adapter.db.data.USER_TABLE
import com.nexters.checkareer.data.adapter.db.data.UserData

@Dao
interface UserDao {

    @Transaction
    @Query("SELECT * FROM $USER_TABLE LIMIT 1" )
    fun getUserSkill(): UserProfile

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserData)

//    @Update
//    suspend fun updateUser(user: UserData)
}