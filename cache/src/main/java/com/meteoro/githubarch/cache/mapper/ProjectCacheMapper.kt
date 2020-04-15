package com.meteoro.githubarch.cache.mapper

import com.meteoro.githubarch.cache.model.project.ProjectCache
import com.meteoro.githubarch.data.model.ProjectData
import javax.inject.Inject

class ProjectCacheMapper @Inject constructor() : CacheMapper<ProjectCache, ProjectData> {

    override fun mapToData(cache: ProjectCache): ProjectData = with(cache) {
        ProjectData(
            id,
            name,
            fullName,
            starCount,
            dateCreated,
            ownerName,
            ownerAvatar,
            isBookMarked
        )
    }

    override fun mapToCache(data: ProjectData): ProjectCache = with(data) {
        ProjectCache(
            id,
            name,
            fullName,
            startCount,
            dateCreated,
            ownerName,
            ownerAvatar,
            isBookmarked
        )
    }
}