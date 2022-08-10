package com.athompson.virgin.di

import com.athompson.virgin.repository.PeopleRepository
import com.athompson.virgin.repository.RoomRepository
import com.athompson.virgin.ui.people.PeopleFragment
import com.athompson.virgin.ui.people.PeopleViewModel
import com.athompson.virgin.ui.people.detail.PersonDetailFragment
import com.athompson.virgin.ui.people.detail.PersonDetailViewModel
import com.athompson.virgin.ui.rooms.RoomsFragment
import com.athompson.virgin.ui.rooms.RoomsViewModel
import com.athompson.virgin.ui.settings.SettingsViewModel
import org.koin.dsl.module

val peopleModule = module {
    factory { PeopleFragment() }
    factory { PeopleViewModel(get()) }
    single { PersonDetailViewModel() }
    single { SettingsViewModel() }
    factory { PeopleRepository(get(), get()) }
    factory { PersonDetailFragment() }
}

val roomModule = module {
    factory { RoomsFragment() }
    factory { RoomsViewModel(get()) }
    factory { RoomRepository(get(), get()) }
}