package com.athompson.virgin
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VirginDirectoryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
