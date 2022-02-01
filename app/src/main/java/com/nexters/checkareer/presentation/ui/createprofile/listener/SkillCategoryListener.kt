package com.nexters.checkareer.presentation.ui.createprofile.listener

import android.view.View
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect

interface SkillCategoryListener {

    fun onSkillCategoryClicked(item: CategorySelect, view: View)
}