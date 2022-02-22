package com.nexters.checkareer.data.source.skill.remote

import com.nexters.checkareer.data.entity.SkillEntity
import com.nexters.checkareer.data.network.ApiService
import com.nexters.checkareer.domain.error.DbError
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SkillRemoteDataSource(
    private val skillApi: ApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): SkillRemote {
    override suspend fun findSkills(): Result<List<SkillEntity>> = withContext(ioDispatcher) {
        val response = skillApi.getSkillList()

        return@withContext if(response.isSuccessful) {
            Result.Success(response.body() ?: listOf())
        } else {
            Result.Error(DbError(""))
        }
    }

    override suspend fun saveSkills(skill: List<SkillEntity>): Result<Unit> {
        TODO("Not yet implemented")
    }
}