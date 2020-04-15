package com.meteoro.githubarch.presentation.view.browse

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.meteoro.githubarch.domain.model.Project
import com.meteoro.githubarch.domain.usecase.BookmarkProject
import com.meteoro.githubarch.domain.usecase.GetProjects
import com.meteoro.githubarch.domain.usecase.UnbookmarkProject
import com.meteoro.githubarch.presentation.fake.FakeDataFactory
import com.meteoro.githubarch.presentation.mapper.ProjectUIMapper
import com.meteoro.githubarch.presentation.resource.ResourceState
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BrowseProjectsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var getProjects: GetProjects

    @MockK
    lateinit var bookmarkProject: BookmarkProject

    @MockK
    lateinit var unbookmarkProject: UnbookmarkProject

    @MockK
    lateinit var projectUIMapper: ProjectUIMapper

    lateinit var viewModel: BrowseProjectsViewModel

    @Before
    fun `before each test`() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = BrowseProjectsViewModel(getProjects, bookmarkProject, unbookmarkProject, projectUIMapper)
        viewModel.itemsResource // because we are using lazy we need to load this first
    }

    @Test
    fun `it should execute with success GetProjects usecase when loading Projects`() {
        // arrange
        val projectList = FakeDataFactory.makeProjectList(10)
        val projectUI = FakeDataFactory.makeProjectUI()
        val paramSlot = slot<DisposableObserver<List<Project>>>()
        every { getProjects.execute(capture(paramSlot)) } returns Unit
        every { projectUIMapper.mapToView(any()) } returns projectUI

        // act
        viewModel.loadProjects()
        paramSlot.captured.onNext(projectList)

        // assert
        val expectedList = projectList.map { projectUI }
        val itemsResource = viewModel.itemsResource
        verify { getProjects.execute(any()) }
        assertEquals(expectedList, itemsResource.value?.data)
        assertEquals(ResourceState.SUCCEEDED, itemsResource.value?.state)
    }

    @Test
    fun `it should execute with success BookmarProject usecase when requested`() {
        // arrange
        val project = FakeDataFactory.makeProject()
        val paramSlot = slot<DisposableCompletableObserver>()
        val expectedParam = BookmarkProject.Params.forProject(project.id)
        every { bookmarkProject.execute(capture(paramSlot), any()) } returns Unit

        // act
        viewModel.bookmarkProject(project.id)
        paramSlot.captured.onComplete()

        // assert
        val itemsResource = viewModel.itemsResource
        verify { bookmarkProject.execute(capture(paramSlot), expectedParam) }
        assertEquals(ResourceState.SUCCEEDED, itemsResource.value?.state)
    }
}