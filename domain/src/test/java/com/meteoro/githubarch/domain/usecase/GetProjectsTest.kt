package com.meteoro.githubarch.domain.usecase

import com.meteoro.githubarch.domain.fake.FakeProjectFactory
import com.meteoro.githubarch.domain.repository.ProjectRepository
import com.meteoro.githubarch.domain.thread.ExecutionThread
import com.meteoro.githubarch.domain.thread.PostExecutionThread
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

class GetProjectsTest {

    @MockK
    lateinit var projectsRepository: ProjectRepository

    @MockK
    lateinit var executionThread: ExecutionThread

    @MockK
    lateinit var postExecutionThread: PostExecutionThread

    private lateinit var getProjects: GetProjects

    @Before
    fun `before each test`() {
        MockKAnnotations.init(this)
        getProjects = GetProjects(projectsRepository, postExecutionThread, executionThread)
    }

    @Test
    fun `it completes`() {
        // arrange
        val projects = FakeProjectFactory.makeProjectList(2)
        every { projectsRepository.getProjects() } returns Flowable.just(projects)

        // act
        val testObserver = getProjects.buildUseCaseObservable().test()

        // assert
        testObserver.assertComplete()
    }

    @Test
    fun `it returns a list of projects`() {
        // arrange
        val projects = FakeProjectFactory.makeProjectList(2)
        every { projectsRepository.getProjects() } returns Flowable.just(projects)

        // act
        val testObserver = getProjects.buildUseCaseObservable().test()

        // assert
        assert(testObserver.values().first() == projects)
    }
}