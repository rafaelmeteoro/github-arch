package com.meteoro.githubarch.data.remote

import com.meteoro.githubarch.data.model.ProjectData
import io.reactivex.Flowable

interface ProjectsRemote {
    fun getProjects(): Flowable<List<ProjectData>>
}