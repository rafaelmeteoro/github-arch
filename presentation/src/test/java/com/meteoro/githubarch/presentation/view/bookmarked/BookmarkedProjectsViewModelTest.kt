package com.meteoro.githubarch.presentation.view.bookmarked

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.meteoro.githubarch.domain.model.Project
import com.meteoro.githubarch.domain.usecase.GetBookmarkedProjects
import com.meteoro.githubarch.presentation.fake.FakeDataFactory
import com.meteoro.githubarch.presentation.mapper.ProjectUIMapper
import com.meteoro.githubarch.presentation.resource.ResourceState
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import io.reactivex.observers.DisposableObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BookmarkedProjectsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var getBookmarkedProjects: GetBookmarkedProjects

    @MockK
    lateinit var projectUiMapper: ProjectUIMapper

    lateinit var viewModel: BookmarkedProjectsViewModel

    @Before
    fun `before each test`() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = BookmarkedProjectsViewModel(getBookmarkedProjects, projectUiMapper)
        viewModel.itemsResources // because we are using lazy we need to load this first
    }

    @Test
    fun `it should execute with success GetBookmarkedProjects usecase when loading Projects`() {
        // arrange
        val projectList = FakeDataFactory.makeProjectList(10)
        val projecUI = FakeDataFactory.makeProjectUI()
        val paramSlot = slot<DisposableObserver<List<Project>>>()
        every { getBookmarkedProjects.execute(capture(paramSlot)) } returns Unit
        every { projectUiMapper.mapToView(any()) } returns projecUI

        // act
        viewModel.loadBookmarkedProjects()
        paramSlot.captured.onNext(projectList)

        // assert
        val expectedList = projectList.map { projecUI }
        val itemsResource = viewModel.itemsResources
        verify { getBookmarkedProjects.execute(any()) }
        assertEquals(expectedList, itemsResource.value?.data)
        assertEquals(ResourceState.SUCCEEDED, itemsResource.value?.state)
    }
}