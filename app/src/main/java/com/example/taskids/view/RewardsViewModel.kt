package com.example.taskids.view

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskids.data.RewardsRepository
import com.example.taskids.models.RewardsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RewardsViewModel @Inject constructor(
    private val repository: RewardsRepository
) : ViewModel() {

    private val _rewards = mutableStateOf<List<RewardsModel>>(emptyList())
    val rewards: State<List<RewardsModel>> = _rewards

    private val _selectedReward = mutableStateOf<RewardsModel?>(null)
    val selectedReward: State<RewardsModel?> = _selectedReward

    init {
        fetchRewards()
    }

    fun fetchRewards() {
        viewModelScope.launch {
            _rewards.value = repository.getAllRewards()
        }
    }

    fun fetchRewardById(id: Int) {
        viewModelScope.launch {
            _selectedReward.value = repository.getRewardById(id)
        }
    }

    fun createReward(reward: RewardsModel) {
        viewModelScope.launch {
            val success = repository.createReward(reward)
            if (success) fetchRewards()
        }
    }

    fun updateReward(id: Int, reward: RewardsModel) {
        viewModelScope.launch {
            val success = repository.updateReward(id, reward)
            if (success) fetchRewards()
        }
    }

    fun deleteReward(id: Int) {
        viewModelScope.launch {
            val success = repository.deleteReward(id)
            if (success) fetchRewards()
        }
    }
}
