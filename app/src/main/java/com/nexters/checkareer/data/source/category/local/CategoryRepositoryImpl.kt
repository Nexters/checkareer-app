package com.nexters.checkareer.data.source.category.local

import com.nexters.checkareer.domain.category.Category
import com.nexters.checkareer.domain.category.CategoryRepository
import com.nexters.checkareer.domain.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val local: CategoryLocal,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): CategoryRepository {

    override suspend fun findByUserId(userId: String): Result<List<Category>> {
        return local.findCategoriesByUserId(userId)
    }
}