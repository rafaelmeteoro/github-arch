package com.meteoro.githubarch.data

import com.meteoro.githubarch.data.mapper.ProjectMapper
import com.meteoro.githubarch.data.store.projects.ProjectsStoreObservableFactory
import com.meteoro.githubarch.domain.model.Project
import com.meteoro.githubarch.domain.repository.ProjectsRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(
    private val mapper: ProjectMapper,
    private val storeObservableFactory: ProjectsStoreObservableFactory
) : ProjectsRepository {

    override fun getProjects(): Flowable<List<Project>> {
        return storeObservableFactory.create()
            .flatMap { store ->
                store.getProjects()
                    .distinctUntilChanged()
            }
            .flatMap { projects ->
                storeObservableFactory.cacheStore()
                    .saveProjects(projects)
                    .andThen(Flowable.just(projects))
            }
            .map { it.map(mapper::mapToDomain) }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return storeObservableFactory.cacheStore().setProjectAsBookmarked(projectId)
    }

    override fun unbookmarkProject(projectId: String): Completable {
        return storeObservableFactory.cacheStore().setProjectAsNotBookmarked(projectId)
    }

    override fun getBookmarkedProjects(): Flowable<List<Project>> {
        return storeObservableFactory.cacheStore()
            .getBookmarkedProjects()
            .map { it.map(mapper::mapToDomain) }
    }
}