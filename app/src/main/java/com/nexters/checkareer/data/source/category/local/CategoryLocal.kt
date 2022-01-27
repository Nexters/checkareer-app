package com.nexters.checkareer.data.source.category.local

import com.nexters.checkareer.domain.category.Category
import com.nexters.checkareer.domain.util.Result

interface CategoryLocal {
    suspend fun findCategoriesByUserId(userId: String): Result<List<Category>>

    suspend fun findCategories(): Result<List<Category>>
}