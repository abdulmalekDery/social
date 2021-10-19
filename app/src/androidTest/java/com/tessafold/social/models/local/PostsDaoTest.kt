package com.tessafold.social.models.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.tessafold.social.getOrAwaitValue
import com.tessafold.social.models.local.dao.PostDBDao
import com.tessafold.social.models.local.models.PostDB
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class PostsDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: TestRoomDatabase
    private lateinit var dao: PostDBDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TestRoomDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.postDBDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertPost() = runBlockingTest {
        val post = PostDB(101,1,"name", "body")
        dao.insert(post)

        val allPosts = dao.observeAllPosts().getOrAwaitValue()

        assertThat(allPosts).contains(post)
    }

    @Test
    fun deletePost() = runBlockingTest {
        val post = PostDB(102,1,"name", "body")
        dao.insert(post)
        dao.deletePost(post)

        val allPosts = dao.observeAllPosts().getOrAwaitValue()

        assertThat(allPosts).doesNotContain(post)
    }

}