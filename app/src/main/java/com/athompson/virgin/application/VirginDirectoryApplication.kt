package com.athompson.virgin.application
import android.app.Application
import com.athompson.virgin.di.peopleModule
import com.athompson.virgin.di.roomModule
import com.athompson.virgin.networking.networkModule
import com.athompson.virgin.preferences.prefModule
import com.athompson.virgin.utility.AppUtilDependency
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
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
}
