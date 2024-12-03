package com.example.codetutor.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {

    private val _completedChallenges = MutableLiveData<Set<Int>>(setOf())
    val completedChallenges: LiveData<Set<Int>> get() = _completedChallenges

    fun markChallengeCompleted(challengeId: Int) {
        val updatedSet = _completedChallenges.value?.toMutableSet()
        updatedSet?.add(challengeId)
        _completedChallenges.value = updatedSet
    }
}