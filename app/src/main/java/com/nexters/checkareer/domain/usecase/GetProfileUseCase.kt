package com.nexters.checkareer.domain.usecase

import com.nexters.checkareer.domain.skill.SkillRepository
import com.nexters.checkareer.domain.user.User
import com.nexters.checkareer.domain.user.UserRepository
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.util.getValue
import com.nexters.checkareer.domain.vo.Profile
import java.lang.Exception
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val skillRepository: SkillRepository,
) {
    suspend operator fun invoke(forceUpdate: Boolean = false): Result<Profile> {
        return try {
            val user = userRepository.findUser(forceUpdate).getValue()
            val skills = skillRepository.findByUserId("user.id").getValue()

            Result.Success(Profile(User("", ""), skills))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}