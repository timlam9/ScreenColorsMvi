package com.timlam.screencolorsmvi

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ScreenColorsMvi : Application() {

    override fun onCreate() {
        super.onCreate()

//        startKoin {
//            androidContext(this@ScreenColorsMvi)
//            modules(dataModules + domainModules)
//        }

    }
}

//val dataModules = listOf(repositoryModule)
//val domainModules = listOf(interactionModule)
