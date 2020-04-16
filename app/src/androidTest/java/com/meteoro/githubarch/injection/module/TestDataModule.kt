package com.meteoro.githubarch.injection.module

import com.meteoro.githubarch.domain.repository.ProjectsRepository
import dagger.Module
import dagger.Provides
import io.mockk.mockk
import javax.inject.Singleton

@Module
abstract class TestDataModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Singleton
        fun bindProjectsRepository(): ProjectsRepository = mockk()
    }
}