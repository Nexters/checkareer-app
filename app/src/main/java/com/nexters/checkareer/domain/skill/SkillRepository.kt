package com.nexters.checkareer.domain.skill

import com.nexters.checkareer.domain.util.Result

interface SkillRepository {

    suspend fun findByUserId(userId: String): Result<List<Skill>>

    suspend fun findAllSkills(): Result<List<Skill>>

    suspend fun saveSkills(skills: List<Skill>): Result<Unit>
}