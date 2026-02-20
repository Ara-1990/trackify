package com.the.trackify.data.apiservise

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("posts")
    suspend fun sendLocations(
        @Body locations: List<LocationDto>
    ): Response<Unit>
}