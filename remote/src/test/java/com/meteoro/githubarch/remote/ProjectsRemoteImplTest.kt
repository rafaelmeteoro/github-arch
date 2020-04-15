package com.meteoro.githubarch.remote

import com.meteoro.githubarch.data.model.ProjectData
import com.meteoro.githubarch.remote.fake.FakeProjectFactory
import com.meteoro.githubarch.remote.mapper.ProjectsResponseDTOMapper
import com.meteoro.githubarch.remote.model.ProjectDTO
import com.meteoro.githubarch.remote.model.ProjectsResponseDTO
import com.meteoro.githubarch.remote.service.GithubTrendingService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

class ProjectsRemoteImplTest {

    @MockK
    lateinit var mapper: ProjectsResponseDTOMapper

    @MockK
    lateinit var service: GithubTrendingService

    private lateinit var projectsRemote: ProjectsRemoteImpl

    @Before
    fun `before each test`() {
        MockKAnnotations.init(this, relaxed = true)
        projectsRemote = ProjectsRemoteImpl(mapper, service)
    }

    @Test
    fun `it should return a list of repositories from service`() {
        // arrange
        val (fakeProjectData, fakeProjectDTOList) = arrangeGetProjects()

        // act
        val testObserver = projectsRemote.getProjects().test()

        // assert
        val expectedList = fakeProjectDTOList.map { fakeProjectData }
        testObserver.assertComplete()
        testObserver.assertValue(expectedList)
    }

    @Test
    fun `it should call the search for repositories using the right params`() {
        arrangeGetProjects()

        // act
        projectsRemote.getProjects().test()

        verify {
            service.searchRepositories(
                query = "language:kotlin",
                order = "stars",
                sortBy = "desc"
            )
        }
    }

    private fun arrangeGetProjects(): Pair<ProjectData, List<ProjectDTO>> {
        val fakeProjectData = FakeProjectFactory.makeProjectData()
        val fakeProjectDTOList = FakeProjectFactory.makeProjectsDTOList(10)
        val fakeResponse = ProjectsResponseDTO(fakeProjectDTOList)

        every { service.searchRepositories(any(), any(), any()) } returns Flowable.just(fakeResponse)
        every { mapper.mapToData(any()) } returns fakeProjectData

        return Pair(fakeProjectData, fakeProjectDTOList)
    }
}