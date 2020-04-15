package com.meteoro.githubarch.data.store.projects

import com.meteoro.githubarch.data.model.ProjectData
import com.meteoro.githubarch.data.remote.ProjectsRemote
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class ProjectsRemoteStore @Inject constructor(
    private val remote: ProjectsRemote
) : ProjectsStore {

    override fun getProjects(): Flowable<List<ProjectData>> {
        return remote.getProjects()
    }

    override fun getBookmarkedProjects(): Flowable<List<ProjectData>> {
        throw ProjectsRemoteStoreException("Getting bookmarked projects isn't supported")
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        throw ProjectsRemoteStoreException("Setting as bookmarked isn't supported")
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        throw ProjectsRemoteStoreException("Setting as not bookmarked isn't supported")
    }

    override fun saveProjects(projects: List<ProjectData>): Completable {
        throw ProjectsRemoteStoreException("Saving isn't supported")
    }

    override fun clearProjects(): Completable {
        throw ProjectsRemoteStoreException("Clearing isn't supported")
    }

    class ProjectsRemoteStoreException(message: String) : UnsupportedOperationException(message)
}