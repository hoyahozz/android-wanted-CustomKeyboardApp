package com.preonboarding.customkeyboard

import android.app.Application
import timber.log.Timber

class WantedApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(object : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                super.log(priority, "Debug[$tag]", message, t)
            }
        })
    }
}
