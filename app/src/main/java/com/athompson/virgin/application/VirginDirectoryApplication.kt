package com.athompson.virgin.application
import android.app.Application
import com.athompson.virgin.baseURLLive
import com.athompson.virgin.baseURLTest
import com.athompson.virgin.di.peopleModule
import com.athompson.virgin.di.roomModule
import com.athompson.virgin.networking.networkModule
import com.athompson.virgin.preferences.Preferences
import com.athompson.virgin.preferences.prefModule
import com.athompson.virgin.utility.AppUtilDependency
import com.athompson.virgin.utility.AppUtility
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class VirginDirectoryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@VirginDirectoryApplication)
            modules(listOf(prefModule, networkModule,AppUtilDependency,
                roomModule,peopleModule
            ))
        }
    }
    companion object: KoinComponent {
        val preferences : Preferences by inject()
        fun getBaseUrl(): String {
            if (preferences.getTestMode()) {
                return baseURLTest
            } else {
                return baseURLLive
            }
        }
    }
}
