package com.nexters.checkareer.data.adapter.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.nexters.checkareer.domain.user.User
import java.util.*

const val USER_TABLE = "user"

@Entity(tableName = USER_TABLE)
data class UserData(
    @PrimaryKey
    val userId: String = UUID.randomUUID().toString(),

    val name: String = "anonymous",

    @ColumnInfo(name = "createdAt", defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: Long = System.currentTimeMillis()
) {
    fun toEntity(): User {
        return User(userId, name)
    }
}
