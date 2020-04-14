package com.meteoro.githubarch.domain.fake

import com.meteoro.githubarch.domain.model.Project
import java.util.*

object FakeProjectFactory {

    fun randomUUUID(): String {
        return UUID.randomUUID().toString()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun makeProject(): Project {
        return Project(
            id = randomUUUID(),
            name = randomUUUID(),
            fullName = randomUUUID(),
            starCount = randomUUUID(),
            dateCreated = randomUUUID(),
            ownerName = randomUUUID(),
            ownerAvatar = randomUUUID(),
            isBookmarked = randomBoolean()
        )
    }

    fun makeProjectList(size: Int): List<Project> {
        val projects = mutableListOf<Project>()
        repeat(size) {
            projects.add(makeProject())
        }
        return projects
    }
}