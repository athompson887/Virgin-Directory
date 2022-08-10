package com.athompson.virgin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.agoda.kakao.switch.KSwitch
import com.agoda.kakao.text.KTextView
import com.athompson.virgin.data.Person
import com.athompson.virgin.ui.people.detail.PersonDetailViewModel
import com.athompson.virgin.ui.people.detail.PersonDetailFragment
import io.mockk.Matcher
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@RunWith(AndroidJUnit4ClassRunner::class)
class PeopleDetailTest {

    private val fragmentViewModel: PersonDetailViewModel =
        mockk(relaxed = true)

    private val fragment = PersonDetailFragment()

    //launches the activity to run the test
    @get:Rule
    val fragmentRule = createRule(fragment, module {
        single() {
            fragmentViewModel
        }
    })

    /*
    *  This test does not work as hoped.  It does invoke the screen and check the field
    *  but the field is not using the data that is passed in the view model.
    *  I have some missing knowledge here - I cannot work out at the current time how to
    *  make the test wait for the ViewModel to return its result before binding the data
    *     */
    @Test
    fun testPersonDetail() {
        fragmentViewModel.selectedPerson.value = Person(
            avatar = "https://randomuser.me/api/portraits/women/21.jpg",
            firstName = "Andrew",
            lastName = "Thompson",
            email =  "a.thompson887@btinternet.com",
            jobtitle =  "Hopeful developer for Virgin Money",
            favouriteColor =  "Green",
            id = "1",
            createdAt = "2022-01-24T17:02:23.729Z")

        onScreen<PersonDetailScreen> {

            fragmentViewModel.selectedPerson.observeForever {
                firstNameText.matches { withText("Andrew") }
            }

         //  lastNameText.matches { withText("Brekke") }
         //  emailText.matches { withText("Crystel.Nicolas61@hotmail.com") }
        }
    }


    class PersonDetailScreen : Screen<PersonDetailScreen>() {
        val firstNameText = KTextView { withId(R.id.first_name_text) }
        val lastNameText = KTextView { withId(R.id.last_name_text) }
        val emailText = KTextView { withId(R.id.email_text) }
    }


    /* Copyright 2019 Google LLC.
   SPDX-License-Identifier: Apache-2.0 */
    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(o: T?) {
                data = o
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }

        this.observeForever(observer)

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }
}