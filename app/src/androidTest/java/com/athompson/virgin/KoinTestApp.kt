package com.athompson.virgin

import android.app.Application
import com.athompson.virgin.di.peopleModule
import com.athompson.virgin.di.roomModule
import com.athompson.virgin.networking.networkModule
import com.athompson.virgin.preferences.prefModule
import com.athompson.virgin.utility.AppUtilDependency
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class KoinTestApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@KoinTestApp)
            modules(listOf(
                prefModule, networkModule, AppUtilDependency,
                roomModule, peopleModule
            ))
        }
    }

    internal fun injectModule(module: Module) {
        loadKoinModules(module)
    }
}