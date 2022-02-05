package com.nexters.checkareer.domain.usecase

import com.nexters.checkareer.data.adapter.db.data.UserProfileData
import com.nexters.checkareer.domain.user.UserRepository
import com.nexters.checkareer.domain.util.Result
import java.lang.Exception
import javax.inject.Inject

class SaveProfileUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(userProfileData: UserProfileData) {
        try {
            val user = userRepository.insertUser(userProfileData)
            println(user)
        } catch (e: Exception) {
            Result.Error(e)
            println(e.message)
        }
    }
}