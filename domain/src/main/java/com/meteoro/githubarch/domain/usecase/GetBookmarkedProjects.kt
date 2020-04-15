package com.meteoro.githubarch.domain.usecase

import com.meteoro.githubarch.domain.model.Project
import com.meteoro.githubarch.domain.repository.ProjectsRepository
import com.meteoro.githubarch.domain.thread.ExecutionThread
import com.meteoro.githubarch.domain.thread.PostExecutionThread
import com.meteoro.githubarch.domain.usecase.base.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject

class GetBookmarkedProjects @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread,
    executionThread: ExecutionThread
) : FlowableUseCase<List<Project>, Unit>(postExecutionThread, executionThread) {

    override fun buildUseCaseObservable(params: Unit?): Flowable<List<Project>> =
        projectsRepository.getBookmarkedProjects()
}