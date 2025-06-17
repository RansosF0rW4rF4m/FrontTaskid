package com.example.taskids.data

import com.example.taskids.models.RewardsModel
import javax.inject.Inject

class RewardsRepository @Inject constructor(
    private val service: RewardsService
) {
    suspend fun getRewardById(id: Int): RewardsModel? {
        return try {
            val response = service.getRewardById(id)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            println("❌ Error fetching reward: ${e.message}")
            null
        }
    }

    suspend fun getAllRewards(): List<RewardsModel> = try {
        service.getAllRewards()
    } catch (e: Exception) {
        println("❌ Error fetching rewards: ${e.message}")
        emptyList()
    }

    suspend fun createReward(reward: RewardsModel): Boolean {
        return try {
            val response = service.createReward(reward)
            response.isSuccessful
        } catch (e: Exception) {
            println("❌ Error creating reward: ${e.message}")
            false
        }
    }

    suspend fun updateReward(id: Int, reward: RewardsModel): Boolean {
        return try {
            val response = service.updateReward(id, reward)
            if (response.isSuccessful) {
                println("✅ Reward updated successfully")
                true
            } else {
                println("❌ Error updating reward: ${response.errorBody()?.string()}")
                false
            }
        } catch (e: Exception) {
            println("❌ Exception updating reward: ${e.message}")
            false
        }
    }

    suspend fun deleteReward(id: Int): Boolean {
        return try {
            val response = service.deleteReward(id)
            if (response.isSuccessful) {
                println("✅ Reward deleted successfully")
                true
            } else {
                println("❌ Error deleting reward: ${response.errorBody()?.string()}")
                false
            }
        } catch (e: Exception) {
            println("❌ Exception deleting reward: ${e.message}")
            false
        }
    }
}