package com.nexters.checkareer.presentation.ui.editprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nexters.checkareer.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EditProfileFragment.newInstance())
                .commitNow()
        }
    }
}