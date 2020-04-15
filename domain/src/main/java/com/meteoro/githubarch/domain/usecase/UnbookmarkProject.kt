package com.meteoro.githubarch.domain.usecase

import com.meteoro.githubarch.domain.repository.ProjectsRepository
import com.meteoro.githubarch.domain.thread.ExecutionThread
import com.meteoro.githubarch.domain.thread.PostExecutionThread
import com.meteoro.githubarch.domain.usecase.base.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class UnbookmarkProject @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    executionThread: ExecutionThread,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<UnbookmarkProject.Params>(postExecutionThread, executionThread) {

    data class Params constructor(val projectId: String) {
        companion object {
            fun forProject(projectId: String) = Params(projectId)
        }
    }

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw UnbookmarkProjectParamsException()
        return projectsRepository.unbookmarkProject(params.projectId)
    }

    class UnbookmarkProjectParamsException : IllegalArgumentException("Params can't be null!")
}