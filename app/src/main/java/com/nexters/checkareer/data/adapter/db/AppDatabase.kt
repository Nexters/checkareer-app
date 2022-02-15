package com.nexters.checkareer.data.adapter.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nexters.checkareer.data.adapter.db.dao.SkillDao
import com.nexters.checkareer.data.adapter.db.dao.UserDao
import com.nexters.checkareer.data.adapter.db.dao.UserSkillDao
import com.nexters.checkareer.data.adapter.db.data.SkillData
import com.nexters.checkareer.data.adapter.db.data.UserData
import com.nexters.checkareer.data.adapter.db.data.UserAndSkillData

@Database(
    entities = [
        UserData::class,
        SkillData::class,
        UserAndSkillData::class
    ],
    version = 1,
    exportSchema = false
)
//@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun skillDao(): SkillDao
    abstract fun userSkillDao(): UserSkillDao

    companion object {
        const val DB_NAME = "ApplicationDatabase.db"
    }



}