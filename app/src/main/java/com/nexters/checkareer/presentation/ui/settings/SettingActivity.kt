package com.nexters.checkareer.presentation.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nexters.checkareer.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SettingFragment.newInstance())
                .commitNow()
        }
    }
}