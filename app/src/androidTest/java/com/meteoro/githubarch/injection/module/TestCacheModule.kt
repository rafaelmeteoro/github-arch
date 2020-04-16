package com.meteoro.githubarch.injection.module

import android.app.Application
import com.meteoro.githubarch.cache.db.AppDatabase
import com.meteoro.githubarch.data.cache.ProjectsCache
import dagger.Module
import dagger.Provides
import io.mockk.mockk

@Module
abstract class TestCacheModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): AppDatabase {
            return AppDatabase.getInstance(application)
        }

        @Provides
        fun bindProjectsRepository(): ProjectsCache = mockk()
    }
}