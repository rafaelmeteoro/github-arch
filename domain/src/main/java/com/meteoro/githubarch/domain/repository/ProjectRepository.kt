package com.meteoro.githubarch.domain.repository

import com.meteoro.githubarch.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Flowable

interface ProjectRepository {
    fun getProjects(): Flowable<List<Project>>
    fun bookmarkProject(projectId: String): Completable
    fun unbookmarkProject(projectId: String): Completable
    fun getBookmarkedProjects(): Flowable<List<Project>>
}