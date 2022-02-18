package com.nexters.checkareer.presentation.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nexters.checkareer.R
import com.nexters.checkareer.presentation.ui.onboarding.OnBoardingActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)



        startActivity(Intent(this, OnBoardingActivity::class.java))
    }
}