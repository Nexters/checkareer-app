package com.nexters.checkareer.domain.usecase

import com.nexters.checkareer.domain.user.UserRepository
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.util.getValue
import com.nexters.checkareer.domain.vo.Profile
import java.lang.Exception
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(forceUpdate: Boolean = false): Result<Profile> {
        return try {
            val userProfile = userRepository.findUserProfile().getValue()
            Result.Success(userProfile)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}