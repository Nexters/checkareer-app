package com.nexters.checkareer.domain.usecase

import com.nexters.checkareer.domain.user.UserRepository
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.util.getValue
import com.nexters.checkareer.domain.vo.LogInInfo
import com.nexters.checkareer.domain.vo.Profile
import java.lang.Exception
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(logInInfo: LogInInfo): Result<Unit> {
        return try {
            val user = userRepository.findUserProfile().getValue()

            val modifiedUser = Profile(user.user.copy(logInInfo = logInInfo), user.skills)
            userRepository.updateUser(modifiedUser)

            Result.Success(Unit)
        } catch (e: Exception) {
            println(e.message)
            Result.Error(e)
        }
    }
}