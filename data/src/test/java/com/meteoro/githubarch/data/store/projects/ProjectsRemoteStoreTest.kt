package com.meteoro.githubarch.data.store.projects

import com.meteoro.githubarch.data.fake.FakeProjectFactory
import com.meteoro.githubarch.data.remote.ProjectsRemote
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

class ProjectsRemoteStoreTest {

    @MockK
    lateinit var remote: ProjectsRemote
    lateinit var projectsRemoteStore: ProjectsRemoteStore

    @Before
    fun `before each test`() {
        MockKAnnotations.init(this, relaxed = true)
        projectsRemoteStore = ProjectsRemoteStore(remote)
    }

    @Test
    fun `it shouuld return a list of Projects`() {
        // arrange
        val fakeProjectDataList = FakeProjectFactory.makeProjectDataList(10)
        every { remote.getProjects() } returns Flowable.just(fakeProjectDataList)

        // act
        val testObservable = projectsRemoteStore.getProjects().test()

        // assert
        testObservable.assertComplete()
        testObservable.assertValue(fakeProjectDataList)
    }

    @Test(expected = ProjectsRemoteStore.ProjectsRemoteStoreException::class)
    fun `it should throws when trying to get a bookmarked Project`() {
        projectsRemoteStore.getBookmarkedProjects()
    }

    @Test(expected = ProjectsRemoteStore.ProjectsRemoteStoreException::class)
    fun `it should throws when trying to bookmarked Project`() {
        projectsRemoteStore.setProjectAsBookmarked("dummyId")
    }

    @Test(expected = ProjectsRemoteStore.ProjectsRemoteStoreException::class)
    fun `it should throws when trying to unbookmarked Project`() {
        projectsRemoteStore.setProjectAsNotBookmarked("dummyId")
    }

    @Test(expected = ProjectsRemoteStore.ProjectsRemoteStoreException::class)
    fun `it should throws when trying to save a list of Project`() {
        projectsRemoteStore.saveProjects(listOf())
    }

    @Test(expected = ProjectsRemoteStore.ProjectsRemoteStoreException::class)
    fun `it should throws when trying to clear`() {
        projectsRemoteStore.clearProjects()
    }
}