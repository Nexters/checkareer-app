package com.nexters.checkareer.presentation.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nexters.checkareer.R
import com.nexters.checkareer.presentation.ui.settings.SettingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }
    }
}