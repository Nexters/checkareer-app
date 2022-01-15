package com.nexters.checkareer

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CheckareerApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}