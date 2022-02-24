package com.nexters.checkareer.domain.usecase

import com.nexters.checkareer.domain.user.UserRepository
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.vo.Profile
import java.lang.Exception
import javax.inject.Inject

class SaveProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(profile: Profile): Result<Unit> {
        return try {
            userRepository.insertUser(profile)
            Result.Success(Unit)
        } catch (e: Exception) {
            println(e.message)
            Result.Error(e)
        }
    }
}