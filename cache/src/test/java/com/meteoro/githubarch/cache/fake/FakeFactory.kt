package com.meteoro.githubarch.cache.fake

import com.meteoro.githubarch.cache.model.config.ConfigCache
import com.meteoro.githubarch.cache.model.project.ProjectCache
import com.meteoro.githubarch.data.model.ProjectData
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object FakeFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun makeProjectData(): ProjectData {
        return ProjectData(
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomBoolean()
        )
    }

    fun makeProjectDataList(size: Int): List<ProjectData> {
        val projects = mutableListOf<ProjectData>()
        repeat(size) {
            projects.add(makeProjectData())
        }
        return projects
    }

    fun makeProjectCache() = ProjectCache(
        randomString(),
        randomString(),
        randomString(),
        randomString(),
        randomString(),
        randomString(),
        randomString(),
        false
    )

    fun makeProjectCacheList(size: Int): List<ProjectCache> {
        val projects = mutableListOf<ProjectCache>()
        repeat(size) {
            projects.add(makeProjectCache())
        }
        return projects
    }

    fun makeConfigCache() = ConfigCache(
        lastCacheTime = System.currentTimeMillis()
    )
}