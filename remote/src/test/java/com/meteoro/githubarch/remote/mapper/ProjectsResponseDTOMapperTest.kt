package com.meteoro.githubarch.remote.mapper

import com.meteoro.githubarch.data.model.ProjectData
import com.meteoro.githubarch.remote.fake.FakeProjectFactory
import com.meteoro.githubarch.remote.model.ProjectDTO
import org.junit.Assert.assertEquals
import org.junit.Test

class ProjectsResponseDTOMapperTest {

    private val mapper = ProjectsResponseDTOMapper()

    @Test
    fun `it maps from Project DTO to Data`() {
        val dto = FakeProjectFactory.makeProjectDTO()
        val data = mapper.mapToData(dto)

        assertEqualsProject(dto, data)
    }

    private fun assertEqualsProject(dto: ProjectDTO, data: ProjectData) {
        assertEquals(dto.id, data.id)
        assertEquals(dto.name, data.name)
        assertEquals(dto.fullName, data.fullName)
        assertEquals(dto.starCount.toString(), data.startCount)
        assertEquals(dto.dateCreated, data.dateCreated)
        assertEquals(dto.owner.ownerName, data.ownerName)
        assertEquals(dto.owner.ownerAvatar, data.ownerAvatar)
        assertEquals(false, data.isBookmarked)
    }
}