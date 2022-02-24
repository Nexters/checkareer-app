package com.nexters.checkareer.data.network

import com.nexters.checkareer.data.network.dto.SkillDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/skill/skills.json")
    suspend fun getSkillList(): Response<List<SkillDto>>

}