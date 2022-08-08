package com.athompson.virgin.application
import android.app.Application
import com.athompson.virgin.networking.networkModule
import com.athompson.virgin.preferences.prefModule
import com.athompson.virgin.repository.peopleRepoModule
import com.athompson.virgin.repository.roomRepoModule
import com.athompson.virgin.ui.people.peopleFragmentModule
import com.athompson.virgin.ui.people.viewModelPeopleModule
import com.athompson.virgin.ui.rooms.roomFragmentModule
import com.athompson.virgin.ui.rooms.viewModelRoomModule
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
            modules(listOf(prefModule, networkModule,
                roomFragmentModule, peopleFragmentModule,
                viewModelRoomModule, viewModelPeopleModule,
                peopleRepoModule, roomRepoModule
            ))
        }
    }
}
