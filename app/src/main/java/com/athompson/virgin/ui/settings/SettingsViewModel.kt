package com.athompson.virgin.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.athompson.virgin.data.Person
import com.athompson.virgin.preferences.Preferences
import com.athompson.virgin.ui.rooms.RoomsViewModel
import com.athompson.virgin.utility.AppUtility
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SettingsViewModel : ViewModel(),KoinComponent {
    val preferences : Preferences by inject()

    private val _testMode = MutableLiveData<Boolean>().apply {
        value = preferences.getTestMode()
    }
    val testMode: LiveData<Boolean> = _testMode

    fun setTestMode(testMode:Boolean) {
        preferences.setTestMode(testMode)
    }
}