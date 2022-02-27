package com.nexters.checkareer.presentation.ui.editprofile.skillorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nexters.checkareer.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeSkillOrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_skill_order_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ChangeSkillOrderFragment.newInstance())
                .commitNow()
        }
    }
}