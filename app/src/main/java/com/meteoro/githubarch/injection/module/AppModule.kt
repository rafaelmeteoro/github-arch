package com.meteoro.githubarch.injection.module

import com.meteoro.githubarch.domain.thread.ExecutionThread
import com.meteoro.githubarch.domain.thread.PostExecutionThread
import com.meteoro.githubarch.thread.AndroidMainThread
import com.meteoro.githubarch.thread.IOThread
import com.meteoro.githubarch.views.MainActivity
import com.meteoro.githubarch.views.bookmarked.BookmarkedProjectsFragment
import com.meteoro.githubarch.views.browse.BrowseProjectsFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    // Threads
    @Binds
    abstract fun bindPostExecutionThread(androidMainThread: AndroidMainThread): PostExecutionThread

    @Binds
    abstract fun IOThread(ioThread: IOThread): ExecutionThread

    // Activities + Fragments
    @ContributesAndroidInjector
    abstract fun contributesBrowserProjectsFragment(): BrowseProjectsFragment

    @ContributesAndroidInjector
    abstract fun contributeBookmarkedProjectsFragment(): BookmarkedProjectsFragment

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
}