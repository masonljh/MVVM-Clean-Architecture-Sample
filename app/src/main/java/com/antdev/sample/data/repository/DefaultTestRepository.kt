package com.antdev.sample.data.repository

import android.util.Log
import com.antdev.sample.di.ApplicationScope
import com.antdev.sample.di.DefaultDispatcher
import com.antdev.sample.domain.model.Person
import com.antdev.sample.domain.model.RequestDto
import com.antdev.sample.domain.model.TestState
import com.antdev.sample.domain.repository.TestRepository
import com.antdev.sample.network.RetrofitClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultTestRepository @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope
) : TestRepository {

    private val testState: MutableStateFlow<TestState> = MutableStateFlow(TestState(false))
    private val testStateFlow: StateFlow<TestState> = testState.asStateFlow()
    override suspend fun getList(): List<Person> {
        return withContext(dispatcher) {
            val retrofit = RetrofitClient.createRetrofitClient("http://10.0.2.2:3000").create(Api::class.java)
            val res = retrofit.getPersonList(RequestDto(a = 2))
            Log.d("TEST", res.body().toString())
            Log.d("TEST", res.errorBody()?.string().toString())
            res.body()?.list ?: emptyList()
        }
    }

    override fun observeState(): StateFlow<TestState> {
        return testStateFlow
    }

    override fun toggleState() {
        testState.update { it.copy(isActive = !it.isActive) }
    }
}