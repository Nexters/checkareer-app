package com.nexters.checkareer.data.adapter.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nexters.checkareer.data.adapter.db.dao.UserDao
import com.nexters.checkareer.data.adapter.db.data.UserProfileData

@Database(
    entities = [UserProfileData::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        const val DB_NAME = "ApplicationDatabase.db"
    }

    abstract fun userDao(): UserDao


}