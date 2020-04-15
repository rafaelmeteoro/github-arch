package com.meteoro.githubarch.presentation.mapper

import com.meteoro.githubarch.presentation.fake.FakeDataFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class ProjectUiMapperTest {

    private val projectMapper = ProjectUIMapper()

    @Test
    fun `map to view maps data`() {
        val project = FakeDataFactory.makeProject()
        val projectForUI = projectMapper.mapToView(project)

        assertEquals(project.id, projectForUI.id)
        assertEquals(project.name, projectForUI.name)
        assertEquals(project.fullName, projectForUI.fullName)
        assertEquals(project.starCount, projectForUI.starCount)
        assertEquals(project.dateCreated, projectForUI.dateCreated)
        assertEquals(project.ownerName, projectForUI.ownerName)
        assertEquals(project.ownerAvatar, projectForUI.ownerAvatar)
        assertEquals(project.isBookmarked, projectForUI.isBookmarked)
    }
}