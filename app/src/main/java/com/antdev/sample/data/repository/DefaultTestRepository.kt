package com.antdev.sample.data.repository

import com.antdev.sample.di.ApplicationScope
import com.antdev.sample.di.DefaultDispatcher
import com.antdev.sample.domain.model.TestState
import com.antdev.sample.domain.repository.TestRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultTestRepository @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope
) : TestRepository {

    private val testState: MutableStateFlow<TestState> = MutableStateFlow(TestState(false))
    private val testStateFlow: StateFlow<TestState> = testState.asStateFlow()

    override fun observeState(): StateFlow<TestState> {
        return testStateFlow
    }

    override fun toggleState() {
        testState.update { it.copy(isActive = !it.isActive) }
    }
}