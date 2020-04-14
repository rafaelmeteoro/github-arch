package com.meteoro.githubarch.domain.usecase

import com.meteoro.githubarch.domain.fake.FakeProjectFactory
import com.meteoro.githubarch.domain.repository.ProjectRepository
import com.meteoro.githubarch.domain.thread.ExecutionThread
import com.meteoro.githubarch.domain.thread.PostExecutionThread
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test

class UnbookmarkProjectsTest {

    @MockK
    lateinit var projectsRepository: ProjectRepository

    @MockK
    lateinit var executionThread: ExecutionThread

    @MockK
    lateinit var postExecutionThread: PostExecutionThread

    private lateinit var unbookmarkProject: UnbookmarkProject

    @Before
    fun `before each test`() {
        MockKAnnotations.init(this)
        unbookmarkProject =
            UnbookmarkProject(projectsRepository, executionThread, postExecutionThread)
    }

    @Test
    fun `it completes`() {
        // arrange
        val projectToBeBookmarked = FakeProjectFactory.makeProject()
        val params = UnbookmarkProject.Params(projectToBeBookmarked.id)
        every { projectsRepository.unbookmarkProject(projectToBeBookmarked.id) } returns Completable.complete()

        // act
        val testObserver = unbookmarkProject.buildUseCaseCompletable(params).test()

        // assert
        testObserver.assertComplete()
    }

    @Test(expected = UnbookmarkProject.UnbookmarkProjectParamsException::class)
    fun `it throws UnbookmarkProjectParamsException when building with no params`() {
        unbookmarkProject.buildUseCaseCompletable().test()
    }
}