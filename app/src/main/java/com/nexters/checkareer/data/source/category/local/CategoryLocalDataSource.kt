package com.nexters.checkareer.data.source.category.local

import com.nexters.checkareer.domain.category.Category
import com.nexters.checkareer.domain.util.Result

class CategoryLocalDataSource : CategoryLocal {

    override suspend fun findCategoriesByUserId(userId: String): Result<List<Category>> {
        return Result.Success(
            listOf(
                Category("1", "Android"),
                Category("2", "IOS")
            )
        )
    }

    override suspend fun findCategories(): Result<List<Category>> {
        return Result.Success(
            listOf(
                Category("1", "프런트엔드 웹"),
                Category("2", "백엔드 웹"),
                Category("3", "웹 개발"),
                Category("4", "프로토타이핑"),
                Category("5", "사용자 경험"),
                Category("6", "유저 인터페이스"),
                Category("7", "소프트웨어 개발"),
                Category("8", "웹 기초"),
                Category("9", "버전 컨트롤"),
                Category("10", "iOS"),
                Category("11", "Android"),
                Category("12", "디자인 패턴"),
            )
        )
    }
}