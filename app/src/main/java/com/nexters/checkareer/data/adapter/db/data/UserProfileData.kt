package com.nexters.checkareer.data.adapter.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.nexters.checkareer.domain.user.User
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import java.util.*

const val USER_TABLE_NAME = "userProfile"

@Entity(tableName = USER_TABLE_NAME)
data class UserProfileData(
    @PrimaryKey
    @ColumnInfo(name = "userId")
    val userId: String = "1",

    @ColumnInfo(name = "name")
    val name: String = "anonymous",

    @ColumnInfo(name = "createdAt", defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "skills")
    val skills: List<CategorySelect>
) {
    fun UserProfileData.toEntity(): User {
        return User(userId, name)
    }
}
