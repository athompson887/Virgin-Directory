package com.athompson.virgin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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

    @Test
    fun testPersonDetail() {
        fragmentViewModel.selectedPerson.value = Person(
            avatar = "https://randomuser.me/api/portraits/women/21.jpg",
            firstName = "Maggie",
            lastName = "Brekke",
            email =  "Crystel.Nicolas61@hotmail.com",
            jobtitle =  "Future Functionality Strategist",
            favouriteColor =  "pink",
            id = "1",
            createdAt = "2022-01-24T17:02:23.729Z")

        onScreen<PersonDetailScreen> {
            firstNameText.matches { withText("Andrew") }
         ///   lastNameText.matches { withText("Brekke") }
        //    emailText.matches { withText("Crystel.Nicolas61@hotmail.com") }
        }

    }


    class PersonDetailScreen : Screen<PersonDetailScreen>() {
        val firstNameText = KTextView { withId(R.id.first_name_text) }
        val lastNameText = KTextView { withId(R.id.last_name_text) }
        val emailText = KTextView { withId(R.id.email_text) }
    }
}