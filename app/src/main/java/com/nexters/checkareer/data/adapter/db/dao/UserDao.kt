package com.nexters.checkareer.data.adapter.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nexters.checkareer.data.adapter.db.data.UserProfileData

@Dao
interface UserDao {

    @Query("SELECT * FROM userProfile WHERE userId LIKE 1")
    fun getUserProfile(): UserProfileData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserProfile(user: UserProfileData)



}