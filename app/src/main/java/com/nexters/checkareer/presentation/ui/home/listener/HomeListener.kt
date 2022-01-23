package com.nexters.checkareer.presentation.ui.home.listener

import com.nexters.checkareer.presentation.ui.home.model.MyProfile
import com.nexters.checkareer.presentation.ui.home.model.OtherProfile

interface HomeListener {

    fun onMyProfileClicked(item: MyProfile)

    fun onOtherProfileClicked(item: OtherProfile)
}