package com.antdev.sample.di

import com.antdev.sample.data.repository.DefaultTestRepository
import com.antdev.sample.domain.repository.TestRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindTestRepository(repository: DefaultTestRepository): TestRepository
}