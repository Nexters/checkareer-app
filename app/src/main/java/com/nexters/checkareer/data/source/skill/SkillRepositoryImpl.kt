package com.nexters.checkareer.data.source.skill

import com.nexters.checkareer.data.source.skill.local.SkillLocal
import com.nexters.checkareer.data.source.skill.remote.SkillRemote
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.skill.SkillRepository
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.util.getValue
import com.nexters.checkareer.domain.vo.SkillLayer
import com.nexters.checkareer.domain.vo.SkillTree
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class SkillRepositoryImpl @Inject constructor(
    private val local: SkillLocal,
    private val remote: SkillRemote,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : SkillRepository {

    override suspend fun syncSkills(): Result<Unit> = withContext(ioDispatcher){
        try {
            val skills = remote.findSkills().getValue()
            local.saveSkills(skills)

            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun findByUserId(userId: String): Result<List<Skill>> {
        return local.findSkillsByUserId(userId)
    }

    override suspend fun findSkillTrees(): Result<List<SkillTree>> = withContext(ioDispatcher) {
        return@withContext try {
            val skills = local.findSkills().getValue()

            val parentSkills = skills.filter { it.layer == 3 }
            val detailSkills = skills.filter { it.layer == 4 }
            Timber.i("parentSkills : $parentSkills")
            Timber.i("detailSkills : $detailSkills")

            val results = parentSkills.map { parent -> parent.toSkillTree(detailSkills.filter { it.parentId == parent.id.toInt() }.map { it.toDetailSkills() })  }

            Result.Success(results)
        }catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun findSkills(): Result<List<Skill>> = withContext(ioDispatcher) {
        return@withContext local.findSkills()
    }

    override suspend fun findSkillsByLayer(skillLayer: SkillLayer): Result<List<Skill>> = withContext(ioDispatcher){
        return@withContext local.findSkillsByLayer(skillLayer)
    }

    override suspend fun saveSkills(skills: List<Skill>): Result<Unit> {
        return local.saveSkills(skills)
    }

    override suspend fun deleteSkills(): Result<Unit> {
        return local.deleteSkills()
    }
}