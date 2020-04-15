package com.meteoro.githubarch.cache

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.meteoro.githubarch.cache.db.AppDatabase
import com.meteoro.githubarch.cache.fake.FakeFactory
import com.meteoro.githubarch.cache.mapper.ProjectCacheMapper
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class ProjectsCacheImplTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var database: AppDatabase
    lateinit var mapper: ProjectCacheMapper
    lateinit var cache: ProjectsCacheImpl

    @Before
    fun `before each test`() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        mapper = ProjectCacheMapper()

        cache = ProjectsCacheImpl(database, mapper)
    }

    @After
    fun `close database`() {
        database.close()
    }

    @Test
    fun `it should get cached projects`() {
        // arrange
        val projectsPreSaved = FakeFactory.makeProjectCacheList(10)
        database.projectsDao().insertProjects(projectsPreSaved)

        // act
        val testObserver = cache.getProjects().test()

        // assert
        val expectedProjects = projectsPreSaved.map(mapper::mapToData)
        testObserver.assertValue(expectedProjects)
    }

    @Test
    fun `it should save projects`() {
        // arrange
        val projectsToBeSaved = FakeFactory.makeProjectDataList(10)

        // act
        val testObserver = cache.saveProjects(projectsToBeSaved).test()

        // assert
        testObserver.assertComplete()
    }

    @Test
    fun `it should clear the tables`() {
        // act
        val testObserver = cache.clearProjects().test()

        // assert
        testObserver.assertComplete()
    }
}