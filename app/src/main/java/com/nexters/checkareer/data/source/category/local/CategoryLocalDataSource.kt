package com.nexters.checkareer.data.source.category.local

import com.nexters.checkareer.domain.category.Category
import com.nexters.checkareer.domain.util.Result
import javax.inject.Inject

class CategoryLocalDataSource : CategoryLocal {

    override suspend fun findCategoriesByUserId(userId: String): Result<List<Category>> {
        return Result.Success(
            listOf(
                Category("1", "Android"),
                Category("2", "IOS")
            )
        )
    }
}