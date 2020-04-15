package com.meteoro.githubarch.injection.module

import com.meteoro.githubarch.data.ProjectsDataRepository
import com.meteoro.githubarch.domain.repository.ProjectsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindProjectsRepository(repo: ProjectsDataRepository): ProjectsRepository
}