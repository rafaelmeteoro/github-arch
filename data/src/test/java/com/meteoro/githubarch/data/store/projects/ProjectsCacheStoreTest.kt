package com.meteoro.githubarch.data.store.projects

import com.meteoro.githubarch.data.cache.ProjectsCache
import com.meteoro.githubarch.data.fake.FakeProjectFactory
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

class ProjectsCacheStoreTest {

    @MockK
    lateinit var cache: ProjectsCache
    private lateinit var projectsCacheStore: ProjectsCacheStore

    @Before
    fun `before each test`() {
        MockKAnnotations.init(this, relaxed = true)
        projectsCacheStore = ProjectsCacheStore(cache)
    }

    @Test
    fun `it should get cached Projects`() {
        // arrange
        val fakeProjectDataList = FakeProjectFactory.makeProjectDataList(10)
        every { cache.getProjects() } returns Flowable.just(fakeProjectDataList)

        // act
        val testObservable = projectsCacheStore.getProjects().test()

        // assert
        testObservable.assertValue(fakeProjectDataList)
    }

    @Test
    fun `it shoud get cached Bookmarked Projects`() {
        // arrange
        val fakeProjectDataList = FakeProjectFactory.makeProjectDataList(10)
        every { cache.getBookmarkedProjects() } returns Flowable.just(fakeProjectDataList)

        // act
        val testObservable = projectsCacheStore.getBookmarkedProjects().test()

        // assert
        testObservable.assertValue(fakeProjectDataList)
    }

    @Test
    fun `it should bookmark a cached Project`() {
        // arrange
        val fakeId = "FakeProjectId"
        every { cache.setProjectsAsBookmarked(any()) } returns Completable.complete()

        // act
        val testObservable = projectsCacheStore.setProjectAsBookmarked(fakeId).test()

        // assert
        testObservable.assertComplete()
        verify { cache.setProjectsAsBookmarked(fakeId) }
    }

    @Test
    fun `it should unbookmark a cached Project`() {
        // arrange
        val fakeId = "FakeProjectId"
        every { cache.setProjectsAsNotBookmarked(any()) } returns Completable.complete()

        // act
        val testObservable = projectsCacheStore.setProjectAsNotBookmarked(fakeId).test()

        // assert
        testObservable.assertComplete()
        verify { cache.setProjectsAsNotBookmarked(fakeId) }
    }

    @Test
    fun `it should save Projects in cache`() {
        // arrange
        val fakeProjectDataList = FakeProjectFactory.makeProjectDataList(10)

        // act
        val testObservable = projectsCacheStore.saveProjects(fakeProjectDataList).test()

        // assert
        testObservable.assertComplete()
        verify {
            cache.saveProjects(fakeProjectDataList)
            cache.setLastCacheTime(any())
        }
    }

    @Test
    fun `it should be able to clear the cache`() {
        // act
        val testObservable = projectsCacheStore.clearProjects().test()

        // assert
        testObservable.assertComplete()
        verify { cache.clearProjects() }
    }
}