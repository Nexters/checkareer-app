package com.nexters.checkareer.domain.usecase

import com.nexters.checkareer.domain.skill.SkillRepository
import com.nexters.checkareer.domain.util.Result
import java.lang.Exception
import javax.inject.Inject

class SyncSkillsUseCase @Inject constructor(
    private val skillRepository: SkillRepository,
) {
    suspend operator fun invoke(): Result<Unit> {
        return try {
            skillRepository.syncSkills()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}