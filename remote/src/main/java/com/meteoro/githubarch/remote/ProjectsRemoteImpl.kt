package com.meteoro.githubarch.remote

import com.meteoro.githubarch.data.model.ProjectData
import com.meteoro.githubarch.data.remote.ProjectsRemote
import com.meteoro.githubarch.remote.mapper.ProjectsResponseDTOMapper
import com.meteoro.githubarch.remote.service.GithubTrendingService
import io.reactivex.Flowable
import javax.inject.Inject

class ProjectsRemoteImpl @Inject constructor(
    private val mapper: ProjectsResponseDTOMapper,
    private val service: GithubTrendingService
) : ProjectsRemote {

    override fun getProjects(): Flowable<List<ProjectData>> {
        return service.searchRepositories(
            query = "language:kotlin",
            order = "stars",
            sortBy = "desc"
        ).map { it.items.map(mapper::mapToData) }
    }
}