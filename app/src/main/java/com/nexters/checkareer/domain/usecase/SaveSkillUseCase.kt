package com.nexters.checkareer.domain.usecase

import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.skill.SkillRepository
import com.nexters.checkareer.domain.user.UserRepository
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.vo.Profile
import java.lang.Exception
import javax.inject.Inject

class SaveSkillUseCase @Inject constructor(
    private val skillRepository: SkillRepository
) {
    suspend operator fun invoke(skills: List<Skill>): Result<Unit> {
        return try {
            skillRepository.saveSkills(skills)
            Result.Success(Unit)
        } catch (e: Exception) {
            println(e.message)
            Result.Error(e)
        }
    }
}