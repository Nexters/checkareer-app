package com.nexters.checkareer.presentation.ui.editprofile.listener

import android.view.View
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect

interface SkillEditListener {

    fun onSkillDeleteClicked(item: Skill, view: View)

    fun onSubSkillAddClick(item: Skill, view: View)
}