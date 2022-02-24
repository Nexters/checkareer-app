package com.nexters.checkareer.domain.skill

import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.vo.SkillLayer
import com.nexters.checkareer.domain.vo.SkillTree

interface SkillRepository {

    suspend fun syncSkills(): Result<Unit>

    suspend fun findByUserId(userId: String): Result<List<Skill>>

    suspend fun findSkillTrees(): Result<List<SkillTree>>

    suspend fun findSkillsByLayer(skillLayer: SkillLayer): Result<List<Skill>>

    suspend fun findSkills(): Result<List<Skill>>

    suspend fun saveSkills(skills: List<Skill>): Result<Unit>

    suspend fun deleteSkills(): Result<Unit>
}