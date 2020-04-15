package com.meteoro.githubarch.injection.module

import com.meteoro.githubarch.BuildConfig
import com.meteoro.githubarch.data.remote.ProjectsRemote
import com.meteoro.githubarch.remote.ProjectsRemoteImpl
import com.meteoro.githubarch.remote.service.GithubTrendingService
import com.meteoro.githubarch.remote.service.GithubTrendingServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideGithubService(): GithubTrendingService {
            return GithubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRepository(repo: ProjectsRemoteImpl): ProjectsRemote
}