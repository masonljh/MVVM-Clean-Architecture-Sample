package com.antdev.sample.domain.repository

import com.antdev.sample.domain.model.TestState
import kotlinx.coroutines.flow.StateFlow

interface TestRepository {
    fun observeState(): StateFlow<TestState>
    fun toggleState()
}