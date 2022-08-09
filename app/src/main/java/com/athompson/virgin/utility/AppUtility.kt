package com.athompson.virgin.utility

import android.content.Context
import com.athompson.virgin.baseURLLive
import com.athompson.virgin.baseURLTest
import com.athompson.virgin.ui.people.PeopleViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent

val AppUtilDependency = module {
    single { AppUtility(androidContext()) }
}

class AppUtility(var context: Context) {

    fun utilityMethodOne(){}
}