package com.meteoro.githubarch.data.cache

import com.meteoro.githubarch.data.model.ProjectData
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface ProjectsCache {
    fun getProjects(): Flowable<List<ProjectData>>
    fun saveProjects(projects: List<ProjectData>): Completable
    fun clearProjects(): Completable
    fun getBookmarkedProjects(): Flowable<List<ProjectData>>
    fun setProjectsAsBookmarked(projectId: String): Completable
    fun setProjectsAsNotBookmarked(projectId: String): Completable
    fun areProjectsCached(): Single<Boolean>
    fun setLastCacheTime(lastCache: Long): Completable
    fun isProjectsCacheExpired(): Single<Boolean>
}