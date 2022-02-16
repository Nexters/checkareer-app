package com.nexters.checkareer.presentation.ui.home.util

import androidx.recyclerview.widget.DiffUtil
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect

class SkillItemDiffCallback : DiffUtil.ItemCallback<Skill>() {

    override fun areItemsTheSame(oldItem: Skill, newItem: Skill): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Skill, newItem: Skill): Boolean {
        return oldItem == newItem
    }
}