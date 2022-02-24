package com.nexters.checkareer.data.source.skill.local

import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.vo.SkillLayer

interface SkillLocal {
    suspend fun findSkillsByUserId(userId: String): Result<List<Skill>>

    suspend fun findSkills(): Result<List<Skill>>

    suspend fun findSkillsByLayer(skillLayer: SkillLayer): Result<List<Skill>>

    suspend fun saveSkills(skill: List<Skill>): Result<Unit>

    suspend fun deleteSkills(): Result<Unit>
}