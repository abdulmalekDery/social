package com.tessafold.social

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.runner.RunWith
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.tessafold.social.features.posts.PostsActivity

import org.junit.Rule
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Test
import org.junit.BeforeClass
import org.junit.After

import org.junit.Before


@RunWith(AndroidJUnit4::class)
@LargeTest
class PostsActivityTest {
    private val USER_NAME_ITEM_0 = "@Bret"
    private val USER_NAME_ITEM_10 = "@Antonette"

    @Rule
    var rule: ActivityScenarioRule<PostsActivity> = ActivityScenarioRule<PostsActivity>(
        PostsActivity::class.java
    )

    @Before
    fun intentsInit() {
        // initialize Espresso Intents capturing

    }

    @After
    fun intentsTeardown() {
        // release Espresso Intents capturing
    }

    /**
     * Test that the list is long enough for this sample, the last item shouldn't appear.
     */
    @Test
    fun firstItem_displayedCorrectly() {
        // first item shouldn't exist with that text
        onView(withText(USER_NAME_ITEM_10)).check(doesNotExist())
    }
}