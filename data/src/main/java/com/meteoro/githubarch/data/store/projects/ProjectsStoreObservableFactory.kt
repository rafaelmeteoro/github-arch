package com.meteoro.githubarch.data.store.projects

import com.meteoro.githubarch.data.cache.ProjectsCache
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class ProjectsStoreObservableFactory @Inject constructor(
    private val cache: ProjectsCache,
    private val remoteStore: ProjectsRemoteStore,
    private val cacheStore: ProjectsCacheStore
) {
    fun create(): Flowable<ProjectsStore> {
        return Flowable.zip(
            cache.isProjectsCacheExpired().toFlowable(),
            cache.areProjectsCached().toFlowable(),
            BiFunction { expired, cached ->
                if (cached && expired.not()) {
                    cacheStore
                } else {
                    remoteStore
                }
            }
        )
    }

    fun cacheStore(): ProjectsCacheStore = cacheStore
}