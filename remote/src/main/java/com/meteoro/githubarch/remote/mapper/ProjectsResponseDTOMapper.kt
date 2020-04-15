package com.meteoro.githubarch.remote.mapper

import com.meteoro.githubarch.data.model.ProjectData
import com.meteoro.githubarch.remote.model.ProjectDTO
import javax.inject.Inject

class ProjectsResponseDTOMapper @Inject constructor() : DTOMapper<ProjectDTO, ProjectData> {

    override fun mapToData(dto: ProjectDTO): ProjectData = with(dto) {
        ProjectData(
            id,
            name,
            fullName,
            starCount.toString(),
            dateCreated,
            owner.ownerName,
            owner.ownerAvatar,
            false
        )
    }
}