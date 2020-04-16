package com.meteoro.githubarch.injection

import android.app.Application
import com.meteoro.githubarch.TestGithubArchApplication
import com.meteoro.githubarch.domain.repository.ProjectsRepository
import com.meteoro.githubarch.injection.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        TestApplicationModule::class,
        TestDataModule::class,
        TestCacheModule::class,
        TestRemoteModule::class,
        AppModule::class,
        PresentationModule::class
    ]
)
interface TestApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestApplicationComponent
    }

    fun inject(app: TestGithubArchApplication)

    // Mocks getters
    fun projectsRepository(): ProjectsRepository
}