package com.nexters.checkareer.domain.usecase

import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.skill.SkillRepository
import com.nexters.checkareer.domain.user.UserRepository
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.util.succeeded
import com.nexters.checkareer.domain.vo.Profile
import java.lang.Exception
import javax.inject.Inject

class EditProfileUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val skillRepository: SkillRepository
) {
    suspend operator fun invoke(profile: Profile): Result<Unit> {
        return try {
            if (userRepository.deleteUser(profile).succeeded) {
                userRepository.insertUser(profile)
            }
            if (skillRepository.deleteSkills().succeeded) {
                skillRepository.saveSkills(profile.skills)
            }
            Result.Success(Unit)
        } catch (e: Exception) {
            println(e.message)
            Result.Error(e)
        }
    }
}