package com.meteoro.githubarch.data.mapper

import com.meteoro.githubarch.data.model.ProjectData
import com.meteoro.githubarch.domain.model.Project
import javax.inject.Inject

class ProjectMapper @Inject constructor() : DataMapper<ProjectData, Project> {

    override fun mapToDomain(data: ProjectData): Project = with(data) {
        Project(id, name, fullName, startCount, dateCreated, ownerName, ownerAvatar, isBookmarked)
    }

    override fun mapToData(domain: Project): ProjectData = with(domain) {
        ProjectData(id, name, fullName, starCount, dateCreated, ownerName, ownerAvatar, isBookmarked)
    }
}