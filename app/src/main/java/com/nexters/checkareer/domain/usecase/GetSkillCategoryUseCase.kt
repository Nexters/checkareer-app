package com.nexters.checkareer.domain.usecase

import com.nexters.checkareer.domain.category.Category
import com.nexters.checkareer.domain.category.CategoryRepository
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.util.getValue
import java.lang.Exception
import javax.inject.Inject

class GetSkillCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(forceUpdate: Boolean = false): Result<List<Category>> {
        return try {
            val categories = categoryRepository.findAllCategory().getValue()

            Result.Success(categories)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}