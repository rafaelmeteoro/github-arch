package com.meteoro.githubarch.cache

import com.meteoro.githubarch.cache.db.AppDatabase
import com.meteoro.githubarch.cache.mapper.ProjectCacheMapper
import com.meteoro.githubarch.cache.model.config.ConfigCache
import com.meteoro.githubarch.data.cache.ProjectsCache
import com.meteoro.githubarch.data.model.ProjectData
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class ProjectsCacheImpl @Inject constructor(
    private val database: AppDatabase,
    private val mapper: ProjectCacheMapper
) : ProjectsCache {

    override fun getProjects(): Flowable<List<ProjectData>> {
        return database.projectsDao().getProjects()
            .map { it.map(mapper::mapToData) }
    }

    override fun saveProjects(projects: List<ProjectData>): Completable {
        return Completable.defer {
            val listToBeSaved = projects.map(mapper::mapToCache)
            database.projectsDao().insertProjects(listToBeSaved)
            Completable.complete()
        }
    }

    override fun clearProjects(): Completable {
        return Completable.defer {
            database.projectsDao().deleteProjects()
            Completable.complete()
        }
    }

    override fun getBookmarkedProjects(): Flowable<List<ProjectData>> {
        return database.projectsDao()
            .getBookmarkedProjects()
            .map { it.map(mapper::mapToData) }
    }

    override fun setProjectsAsBookmarked(projectId: String): Completable {
        return Completable.defer {
            database.projectsDao().setBookmarkStatus(true, projectId)
            Completable.complete()
        }
    }

    override fun setProjectsAsNotBookmarked(projectId: String): Completable {
        return Completable.defer {
            database.projectsDao().setBookmarkStatus(false, projectId)
            Completable.complete()
        }
    }

    override fun areProjectsCached(): Single<Boolean> {
        return database.projectsDao().getProjects().isEmpty.map { !it }
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            database.configDao().insertConfig(ConfigCache(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isProjectsCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()

        return database.configDao().getConfig()
            .onErrorReturn { ConfigCache(lastCacheTime = 0) }
            .map { currentTime - it.lastCacheTime > expirationTime }
    }
}