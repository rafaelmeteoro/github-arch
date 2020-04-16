package com.meteoro.githubarch.views.bookmarked

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.meteoro.githubarch.TestGithubArchApplication
import com.meteoro.githubarch.fake.FakeFactory
import io.mockk.every
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookmarkedProjectsFragmentTest {

    private val applicationComponent by lazy { TestGithubArchApplication.appComponent() }

    private val projectsRepository by lazy { applicationComponent.projectsRepository() }

    @Test
    fun itDisplayAListOfProjects() {
        // arrange
        val projects = FakeFactory.makeProjectList(10)
        every { projectsRepository.getBookmarkedProjects() } returns Flowable.just(projects)

        // act
        launchFragmentInContainer<BookmarkedProjectsFragment>()

        // assert
        projects.forEachIndexed { index, project ->
            robot { scrollToViewWithIndex(index) } verify { itemShowText(project.fullName) }
        }
    }
}