package com.meteoro.githubarch.views.browse

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.meteoro.githubarch.TestGithubArchApplication
import com.meteoro.githubarch.fake.FakeFactory
import io.mockk.every
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BrowseProjectsFragmentTest {

    private val applicationComponent by lazy { TestGithubArchApplication.appComponent() }

    private val projectsRepository by lazy { applicationComponent.projectsRepository() }

    @Test
    fun itShowsAListOfProjects() {
        // arrange
        val projects = FakeFactory.makeProjectList(10)
        every { projectsRepository.getProjects() } returns Flowable.just(projects)

        // act
        launchFragmentInContainer<BrowseProjectsFragment>()

        // assert
        projects.forEachIndexed { index, project ->
            robot { scrollToViewWithIndex(index) } verify { itemShowText(project.fullName) }
        }
    }
}