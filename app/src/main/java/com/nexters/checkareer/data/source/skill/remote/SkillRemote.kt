package com.nexters.checkareer.data.source.skill.remote

import com.nexters.checkareer.data.entity.SkillEntity
import com.nexters.checkareer.domain.util.Result

interface SkillRemote {
    suspend fun findSkills(): Result<List<SkillEntity>>

    suspend fun saveSkills(skill: List<SkillEntity>): Result<Unit>
}