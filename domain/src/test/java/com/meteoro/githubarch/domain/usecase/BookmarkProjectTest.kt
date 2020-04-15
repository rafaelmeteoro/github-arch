package com.meteoro.githubarch.domain.usecase

import com.meteoro.githubarch.domain.fake.FakeProjectFactory
import com.meteoro.githubarch.domain.repository.ProjectsRepository
import com.meteoro.githubarch.domain.thread.ExecutionThread
import com.meteoro.githubarch.domain.thread.PostExecutionThread
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test

class BookmarkProjectTest {

    @MockK
    lateinit var projectsRepository: ProjectsRepository

    @MockK
    lateinit var executionThread: ExecutionThread

    @MockK
    lateinit var postExecutionThread: PostExecutionThread

    private lateinit var bookMarkProject: BookmarkProject

    @Before
    fun `before each test`() {
        MockKAnnotations.init(this)
        bookMarkProject = BookmarkProject(projectsRepository, postExecutionThread, executionThread)
    }

    @Test
    fun `it completes`() {
        // arrange
        val projectToBeBookmarked = FakeProjectFactory.makeProject()
        val params = BookmarkProject.Params(projectToBeBookmarked.id)
        every { projectsRepository.bookmarkProject(projectToBeBookmarked.id) } returns Completable.complete()

        // act
        val testObserver = bookMarkProject.buildUseCaseCompletable(params).test()

        // assert
        testObserver.assertComplete()
    }

    @Test(expected = BookmarkProject.BookmarkProjectParamsException::class)
    fun `it throws BookmakProjectParamsException when building with no params`() {
        bookMarkProject.buildUseCaseCompletable().test()
    }
}