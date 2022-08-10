package com.athompson.virgin.preferences

import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val prefModule = module {
    single { Preferences(androidContext()) }
}

class Preferences(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    private val testModeKey = "testMode"

    init {
        setTestMode(true)
    }

    fun setTestMode(testMode: Boolean) {
        preferences.edit().putBoolean(testModeKey, testMode).apply()
    }
    fun getTestModeLive(): Boolean {
        return preferences.getBoolean(testModeKey, false)
    }
    fun getTestMode(): Boolean {
        return preferences.getBoolean(testModeKey, false)
    }
}