package com.meteoro.githubarch.data.store.projects

import com.meteoro.githubarch.data.model.ProjectData
import io.reactivex.Completable
import io.reactivex.Flowable

interface ProjectsStore {
    fun getProjects(): Flowable<List<ProjectData>>
    fun getBookmarkedProjects(): Flowable<List<ProjectData>>
    fun setProjectAsBookmarked(projectId: String): Completable
    fun setProjectAsNotBookmarked(projectId: String): Completable
    fun saveProjects(projects: List<ProjectData>): Completable
    fun clearProjects(): Completable
}