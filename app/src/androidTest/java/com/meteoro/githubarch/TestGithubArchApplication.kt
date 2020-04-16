package com.meteoro.githubarch

import android.app.Application
import androidx.test.InstrumentationRegistry
import com.meteoro.githubarch.injection.DaggerTestApplicationComponent
import com.meteoro.githubarch.injection.TestApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TestGithubArchApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private lateinit var appComponent: TestApplicationComponent

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    companion object {
        @Suppress("DEPRECATION")
        fun appComponent(): TestApplicationComponent {
            return (InstrumentationRegistry.getTargetContext().applicationContext as TestGithubArchApplication).appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerTestApplicationComponent
            .builder()
            .application(this)
            .build()

        appComponent.inject(this)
    }
}