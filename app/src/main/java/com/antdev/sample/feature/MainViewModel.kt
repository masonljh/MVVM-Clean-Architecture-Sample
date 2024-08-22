package com.antdev.sample.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.antdev.sample.domain.model.TestState
import com.antdev.sample.domain.repository.TestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val testRepository: TestRepository
): ViewModel() {

    private val _uiState = testRepository.observeState()
    val uiState: StateFlow<TestState> = _uiState

    init {
        viewModelScope.launch {
            testRepository.observeState().collect()
        }
        toggleAuto()
    }

    fun toggleAuto() {
        viewModelScope.launch {
            while (true) {
                delay(1000)
                testRepository.toggleState()
            }
        }
    }
}