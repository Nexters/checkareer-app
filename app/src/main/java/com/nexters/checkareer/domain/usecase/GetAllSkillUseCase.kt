package com.nexters.checkareer.domain.usecase

import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.skill.SkillRepository
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.util.getValue
import java.lang.Exception
import javax.inject.Inject

class GetAllSkillUseCase @Inject constructor(
    private val skillRepository: SkillRepository
) {
    suspend operator fun invoke(forceUpdate: Boolean = false): Result<List<Skill>> {
        return try {
            val categories = skillRepository.findSkills().getValue()

            Result.Success(categories)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}