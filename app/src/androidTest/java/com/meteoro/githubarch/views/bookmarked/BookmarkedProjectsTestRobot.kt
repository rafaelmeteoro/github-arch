package com.meteoro.githubarch.views.bookmarked

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.meteoro.githubarch.R
import com.meteoro.githubarch.views.browse.BrowseAdapter

fun robot(func: BookmarkedProjectsTestRobot.() -> Unit) =
    BookmarkedProjectsTestRobot().apply { func() }

internal infix fun BookmarkedProjectsTestRobot.verify(func: BookmarkedProjectsTestResult.() -> Unit) =
    BookmarkedProjectsTestResult().apply { func() }

open class BookmarkedProjectsTestRobot {

    fun scrollToViewWithIndex(index: Int) {
        onView(ViewMatchers.withId(R.id.recycler_projects))
            .perform(RecyclerViewActions.scrollToPosition<BrowseAdapter.ViewHolder>(index))
    }
}