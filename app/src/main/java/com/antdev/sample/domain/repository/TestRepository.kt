package com.antdev.sample.domain.repository

import com.antdev.sample.domain.model.Person
import com.antdev.sample.domain.model.TestState
import kotlinx.coroutines.flow.StateFlow

interface TestRepository {
    suspend fun getList(): List<Person>
    fun observeState(): StateFlow<TestState>
    fun toggleState()
}