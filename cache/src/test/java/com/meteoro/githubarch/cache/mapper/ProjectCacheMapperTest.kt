package com.meteoro.githubarch.cache.mapper

import com.meteoro.githubarch.cache.fake.FakeFactory
import com.meteoro.githubarch.cache.model.project.ProjectCache
import com.meteoro.githubarch.data.model.ProjectData
import org.junit.Assert.assertEquals
import org.junit.Test

class ProjectCacheMapperTest {

    private val mapper = ProjectCacheMapper()

    @Test
    fun `it maps from project cache to project data`() {
        val cache = FakeFactory.makeProjectCache()
        val data = mapper.mapToData(cache)

        assertEqualData(cache, data)
    }

    @Test
    fun `it maps from project data to project cache`() {
        val cache = FakeFactory.makeProjectData()
        val model = mapper.mapToCache(cache)

        assertEqualData(model, cache)
    }

    private fun assertEqualData(model: ProjectCache, cache: ProjectData) {
        assertEquals(model.id, cache.id)
        assertEquals(model.name, cache.name)
        assertEquals(model.fullName, cache.fullName)
        assertEquals(model.dateCreated, cache.dateCreated)
        assertEquals(model.starCount, cache.startCount)
        assertEquals(model.ownerName, cache.ownerName)
        assertEquals(model.ownerAvatar, cache.ownerAvatar)
        assertEquals(model.isBookMarked, cache.isBookmarked)
    }
}