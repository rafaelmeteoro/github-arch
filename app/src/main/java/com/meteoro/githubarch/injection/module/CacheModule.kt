package com.meteoro.githubarch.injection.module

import android.app.Application
import com.meteoro.githubarch.cache.ProjectsCacheImpl
import com.meteoro.githubarch.cache.db.AppDatabase
import com.meteoro.githubarch.data.cache.ProjectsCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideDataBase(application: Application): AppDatabase {
            return AppDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectsRepository(repo: ProjectsCacheImpl): ProjectsCache
}