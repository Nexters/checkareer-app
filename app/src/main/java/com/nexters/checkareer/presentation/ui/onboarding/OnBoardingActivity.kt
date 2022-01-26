package com.nexters.checkareer.presentation.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nexters.checkareer.R
import com.nexters.checkareer.presentation.ui.home.HomeFragment

class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, OnBoardingFragment.newInstance())
                .commitNow()
        }
    }
}