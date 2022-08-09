package com.athompson.virgin.utility

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val AppUtilDependency = module {

    single { AppUtility(androidContext()) }
}

class AppUtility(var context: Context) {
    //Use the context object as common reusable throughout methods.
    //Utility method which needs context
    fun utilityMethodOne(){

    }
}