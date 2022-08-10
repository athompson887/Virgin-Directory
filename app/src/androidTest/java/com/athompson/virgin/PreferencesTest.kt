package com.athompson.virgin

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.agoda.kakao.switch.KSwitch
import com.athompson.virgin.ui.settings.SettingsFragment
import com.athompson.virgin.ui.settings.SettingsViewModel
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module

@RunWith(AndroidJUnit4ClassRunner::class)
class PreferencesTest {

    private val fragmentViewModel: SettingsViewModel =
        mockk(relaxed = true)
    private val fragment = SettingsFragment()

    //launches the activity to run the test
    @get:Rule
    val fragmentRule = createRule(fragment, module {
        single() {
            fragmentViewModel
        }
    })

    @Test
    fun testLiveSwitchSettings() {
        onScreen<SettingsScreen> {
            liveSwitch.click()
            onView(withId(R.id.live_switch)).check(matches(isChecked()))

            onScreen<SettingsScreen> {
                intercept {
                    onViewInteraction {
                        onCheck(true) { interaction, assertion ->
                            try {
                                interaction.check(assertion)
                            } catch (ex: Throwable) {
                                interaction.check(assertion)
                            }
                        }
                    }
                }
            }
        }
    }


    class SettingsScreen : Screen<SettingsScreen>() {
        val liveSwitch = KSwitch { withId(R.id.live_switch) }
    }
}