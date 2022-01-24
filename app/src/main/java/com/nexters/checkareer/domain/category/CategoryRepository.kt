package com.nexters.checkareer.domain.category

import com.nexters.checkareer.domain.util.Result

interface CategoryRepository {

    suspend fun findByUserId(userId: String): Result<List<Category>>
}