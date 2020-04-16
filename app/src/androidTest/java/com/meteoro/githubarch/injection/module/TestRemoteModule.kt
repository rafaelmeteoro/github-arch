package com.meteoro.githubarch.injection.module

import com.meteoro.githubarch.data.remote.ProjectsRemote
import com.meteoro.githubarch.remote.service.GithubTrendingService
import dagger.Module
import dagger.Provides
import io.mockk.mockk

@Module
abstract class TestRemoteModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideGithubService(): GithubTrendingService = mockk()

        @Provides
        fun bindProjectsRepository(): ProjectsRemote = mockk()
    }
}