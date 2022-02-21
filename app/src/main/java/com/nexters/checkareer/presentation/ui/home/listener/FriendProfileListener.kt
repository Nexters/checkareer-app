package com.nexters.checkareer.presentation.ui.home.listener

import android.view.View
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.vo.Profile
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect

interface FriendProfileListener {

    fun onProfileClicked(item: Profile, view: View)
}