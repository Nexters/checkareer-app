package com.nexters.checkareer.data.adapter.db.dao

import androidx.room.*
import com.nexters.checkareer.data.adapter.db.data.UserProfile
import com.nexters.checkareer.data.adapter.db.data.UserData

@Dao
interface UserDao {

    @Transaction
    @Query("SELECT * FROM user" )
    fun getUserProfile(): UserProfile

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserData)

//    @Update
//    suspend fun updateUser(user: UserData)
}