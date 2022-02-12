package com.nexters.checkareer.data.source.skill.local

import com.nexters.checkareer.data.adapter.db.dao.SkillDao
import com.nexters.checkareer.data.adapter.db.data.SkillData
import com.nexters.checkareer.domain.error.DbError
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.util.Result
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
                Skill("2", "IOS")
            )
        )
    }

    override suspend fun findSkills(): Result<List<Skill>> {
        return Result.Success(
            listOf(
                Skill("1", "프런트엔드 웹"),
                Skill("2", "백엔드 웹"),
                Skill("3", "웹 개발"),
                Skill("4", "프로토타이핑"),
                Skill("5", "사용자 경험"),
                Skill("6", "유저 인터페이스"),
                Skill("7", "소프트웨어 개발"),
                Skill("8", "웹 기초"),
                Skill("9", "버전 컨트롤"),
                Skill("10", "iOS"),
                Skill("11", "Android"),
                Skill("12", "디자인 패턴"),
            )
        )
    }

    override suspend fun saveSkills(skills: List<Skill>): Result<Unit> = withContext(ioDispatcher){
        return@withContext try {
            skillDao.saveSkill(skills.map { SkillData(it.id, it.name) })
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DbError(e.toString()))
        }
    }
}