package com.meteoro.githubarch.presentation.fake

import com.meteoro.githubarch.domain.model.Project
import com.meteoro.githubarch.presentation.model.ProjectUI
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object FakeDataFactory {

    fun randonString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun makeProjectUI() =
        ProjectUI(
            randonString(),
            randonString(),
            randonString(),
            randonString(),
            randonString(),
            randonString(),
            randonString(),
            randomBoolean()
        )

    fun makeProject() =
        Project(
            randonString(),
            randonString(),
            randonString(),
            randonString(),
            randonString(),
            randonString(),
            randonString(),
            randomBoolean()
        )

    fun makeProjectList(count: Int): List<Project> {
        val projects = mutableListOf<Project>()
        repeat(count) {
            projects.add(makeProject())
        }
        return projects
    }
}