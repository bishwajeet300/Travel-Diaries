package com.bishwajeet.traveldiaries.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import com.bishwajeet.traveldiaries.R
import com.bishwajeet.traveldiaries.view.profile.ReviewRecyclerAdapter
import com.bishwajeet.traveldiaries.view.splash.SplashActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@MediumTest
class SplashActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(SplashActivity::class.java)


    @Test
    fun checkAppName() {
        onView(withId(R.id.tvAppName))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.app_name)))
    }


    @Test
    fun checkContentLoading() {
        onView(withId(R.id.tvAppName))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.app_name)))

        Thread.sleep(10000)

        onView(withId(R.id.tvTourName))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.tour_name)))
    }


    @Test
    fun tapRecyclerViewItem() {
        onView(withId(R.id.tvAppName))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.app_name)))

        Thread.sleep(10000)

        onView(withId(R.id.rvReviews))
            .perform(actionOnItemAtPosition<ReviewRecyclerAdapter.ReviewViewHolder>(5, click()))
    }


    @Test
    fun scrollTestRecyclerView() {
        onView(withId(R.id.tvAppName))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.app_name)))

        Thread.sleep(10000)

        onView(withId(R.id.rvReviews))
            .perform(RecyclerViewActions.scrollToPosition<ReviewRecyclerAdapter.ReviewViewHolder>(20))
            .perform(RecyclerViewActions.scrollToPosition<ReviewRecyclerAdapter.ReviewViewHolder>(30))
            .perform(RecyclerViewActions.scrollToPosition<ReviewRecyclerAdapter.ReviewViewHolder>(40))
            .perform(actionOnItemAtPosition<ReviewRecyclerAdapter.ReviewViewHolder>(10, click()))
    }
}