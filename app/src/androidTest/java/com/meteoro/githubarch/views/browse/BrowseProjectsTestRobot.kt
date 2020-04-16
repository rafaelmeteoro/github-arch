package com.meteoro.githubarch.views.browse

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.meteoro.githubarch.R

internal fun robot(func: BrowseProjectsTestRobot.() -> Unit) =
    BrowseProjectsTestRobot().apply { func() }

internal infix fun BrowseProjectsTestRobot.verify(func: BrowseProjectsTestResult.() -> Unit) =
    BrowseProjectsTestResult().apply { func() }

open class BrowseProjectsTestRobot {

    fun scrollToViewWithIndex(index: Int) {
        onView(ViewMatchers.withId(R.id.projects_recycler_view))
            .perform(RecyclerViewActions.scrollToPosition<BrowseAdapter.ViewHolder>(index))
    }
}