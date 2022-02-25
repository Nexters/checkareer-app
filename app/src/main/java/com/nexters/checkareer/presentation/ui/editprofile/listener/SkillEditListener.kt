package com.nexters.checkareer.presentation.ui.editprofile.listener

import android.view.View
import com.nexters.checkareer.domain.vo.SkillTree

interface SkillEditListener {

    fun onSkillDeleteClicked(item: SkillTree, view: View)

    fun onSubSkillAddClick(item: SkillTree, view: View)
}