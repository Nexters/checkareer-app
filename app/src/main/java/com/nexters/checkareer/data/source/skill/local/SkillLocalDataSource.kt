package com.nexters.checkareer.data.source.skill.local

import com.nexters.checkareer.data.adapter.db.dao.SkillDao
import com.nexters.checkareer.data.adapter.db.data.SkillData
import com.nexters.checkareer.domain.error.DbError
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.vo.SkillLayer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class SkillLocalDataSource(
    private val skillDao: SkillDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : SkillLocal {

    override suspend fun findSkillsByUserId(userId: String): Result<List<Skill>> {
        return Result.Success(
            listOf(
                Skill("1", "Android"),
                Skill("2", "IOS"),
                Skill("3", "웹 개발"),
                Skill("4", "프로토타이핑"),
            )
        )
    }

    override suspend fun findSkills(): Result<List<Skill>> {
        return Result.Success(
            skillDao.getSkills().map { it.toEntity() }
        )
    }

    override suspend fun findSkillsByLayer(skillLayer: SkillLayer): Result<List<Skill>> {
        return Result.Success(
            skillDao.getSkillsByLayer(skillLayer.value).map { it.toEntity() }
        )
    }

    override suspend fun saveSkills(skills: List<Skill>): Result<Unit> = withContext(ioDispatcher) {
        return@withContext try {
            skills.map { SkillData(it.id, it.name, it.parentId, it.layer) }.run {
                skillDao.saveSkills(this)
                Result.Success(Unit)
            }
        } catch (e: Exception) {
            Result.Error(DbError(e.toString()))
        }
    }

    override suspend fun deleteSkills(): Result<Unit> = withContext(ioDispatcher) {
        return@withContext try {
            skillDao.deleteUserSkill()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DbError(e.toString()))
        }
    }
}