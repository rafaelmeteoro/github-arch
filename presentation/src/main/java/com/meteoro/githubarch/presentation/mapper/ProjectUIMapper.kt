package com.meteoro.githubarch.presentation.mapper

import com.meteoro.githubarch.domain.model.Project
import com.meteoro.githubarch.presentation.model.ProjectUI
import javax.inject.Inject

class ProjectUIMapper @Inject constructor() : UIMapper<ProjectUI, Project> {

    override fun mapToView(domain: Project): ProjectUI = with(domain) {
        ProjectUI(
            id,
            name,
            fullName,
            starCount,
            dateCreated,
            ownerName,
            ownerAvatar,
            isBookmarked
        )
    }
}