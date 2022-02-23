package com.nexters.checkareer.data.network

import com.nexters.checkareer.data.entity.SkillEntity
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/skill/skills.json")
    suspend fun getSkillList(): Response<List<SkillEntity>>

}