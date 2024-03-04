package com.valance.english

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EnglishApp: Application() {
    companion object {
        lateinit var instance: EnglishApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}