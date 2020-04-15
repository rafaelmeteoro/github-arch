package com.meteoro.githubarch.data.model

data class ProjectData(
    val id: String,
    val name: String,
    val fullName: String,
    val startCount: String,
    val dateCreated: String,
    val ownerName: String,
    val ownerAvatar: String,
    val isBookmarked: Boolean
)