package com.meteoro.githubarch.data.fake

import com.meteoro.githubarch.data.model.ProjectData
import com.meteoro.githubarch.domain.model.Project
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object FakeProjectFactory {

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
            id = randomString(),
            name = randomString(),
            fullName = randomString(),
            startCount = randomString(),
            dateCreated = randomString(),
            ownerName = randomString(),
            ownerAvatar = randomString(),
            isBookmarked = randomBoolean()
        )
    }

    fun makeProjectDataList(size: Int): List<ProjectData> {
        val projects = mutableListOf<ProjectData>()
        repeat(size) {
            projects.add(makeProjectData())
        }
        return projects
    }

    fun makeProject(): Project {
        return Project(
            id = randomString(),
            name = randomString(),
            fullName = randomString(),
            starCount = randomString(),
            dateCreated = randomString(),
            ownerName = randomString(),
            ownerAvatar = randomString(),
            isBookmarked = randomBoolean()
        )
    }
}