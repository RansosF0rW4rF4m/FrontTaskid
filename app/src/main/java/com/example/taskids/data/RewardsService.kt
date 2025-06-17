package com.example.taskids.data

import com.example.taskids.models.RewardsModel
import retrofit2.Response
import retrofit2.http.*

interface RewardsService {
    @GET("api/rewards/rewards/")
    suspend fun getAllRewards(): List<RewardsModel>

    @GET("api/rewards/rewards/{id}/")
    suspend fun getRewardById(@Path("id") id: Int): Response<RewardsModel>

    @POST("api/rewards/rewards/")
    suspend fun createReward(@Body reward: RewardsModel): Response<RewardsModel>

    @PUT("api/rewards/rewards/{id}/")
    suspend fun updateReward(
        @Path("id") id: Int,
        @Body reward: RewardsModel
    ): Response<RewardsModel>

    @DELETE("api/rewards/rewards/{id}/")
    suspend fun deleteReward(@Path("id") id: Int): Response<Unit>
}