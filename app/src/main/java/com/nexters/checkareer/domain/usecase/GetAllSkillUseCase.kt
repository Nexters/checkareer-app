package com.nexters.checkareer.domain.usecase

import com.nexters.checkareer.domain.skill.SkillRepository
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.vo.SkillTree
import java.lang.Exception
import javax.inject.Inject

class GetAllSkillUseCase @Inject constructor(
    private val skillRepository: SkillRepository
) {
    suspend operator fun invoke(forceUpdate: Boolean = false): Result<List<SkillTree>> {
        return try {
            skillRepository.findSkillTrees()
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}