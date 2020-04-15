package com.meteoro.githubarch.data.store.projects

import com.meteoro.githubarch.data.cache.ProjectsCache
import com.meteoro.githubarch.data.model.ProjectData
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class ProjectsCacheStore @Inject constructor(
    private val cache: ProjectsCache
) : ProjectsStore {

    override fun getProjects(): Flowable<List<ProjectData>> {
        return cache.getProjects()
    }

    override fun getBookmarkedProjects(): Flowable<List<ProjectData>> {
        return cache.getBookmarkedProjects()
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return cache.setProjectsAsBookmarked(projectId)
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return cache.setProjectsAsNotBookmarked(projectId)
    }

    override fun saveProjects(projects: List<ProjectData>): Completable {
        return cache.saveProjects(projects)
            .concatWith(cache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun clearProjects(): Completable {
        return cache.clearProjects()
    }
}