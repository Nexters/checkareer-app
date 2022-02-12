package com.nexters.checkareer.data.source.skill.local

import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.skill.SkillRepository
import com.nexters.checkareer.domain.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SkillRepositoryImpl @Inject constructor(
    private val local: SkillLocal,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): SkillRepository {

    override suspend fun findByUserId(userId: String): Result<List<Skill>> {
        return local.findSkillsByUserId(userId)
    }

    override suspend fun findAllSkills(): Result<List<Skill>> {
        return local.findSkills()
    }

    override suspend fun saveSkills(skills: List<Skill>): Result<Unit> {
        return local.saveSkills(skills)
    }
}