package com.meteoro.githubarch.remote.fake

import com.meteoro.githubarch.data.model.ProjectData
import com.meteoro.githubarch.remote.model.OwnerDTO
import com.meteoro.githubarch.remote.model.ProjectDTO
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

    fun makeRandomOwnerDTO() = OwnerDTO(randomString(), randomString())

    fun makeProjectDTO() =
        ProjectDTO(
            randomString(),
            randomString(),
            randomString(),
            randomInt(),
            randomString(),
            makeRandomOwnerDTO()
        )

    fun makeProjectsDTOList(size: Int = randomInt()): List<ProjectDTO> {
        val projects = mutableListOf<ProjectDTO>()
        repeat(size) {
            projects.add(makeProjectDTO())
        }
        return projects
    }
}