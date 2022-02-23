package com.nexters.checkareer.data.source.skill.remote

import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.util.Result

interface SkillRemote {
    suspend fun findSkills(): Result<List<Skill>>

    suspend fun saveSkills(skill: List<Skill>): Result<Unit>
}