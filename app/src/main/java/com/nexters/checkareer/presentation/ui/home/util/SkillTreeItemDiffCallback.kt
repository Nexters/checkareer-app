package com.nexters.checkareer.presentation.ui.home.util

import androidx.recyclerview.widget.DiffUtil
import com.nexters.checkareer.domain.vo.SkillTree

class SkillTreeItemDiffCallback : DiffUtil.ItemCallback<SkillTree>() {

    override fun areItemsTheSame(oldItem: SkillTree, newItem: SkillTree): Boolean {
        return oldItem.skill.id == newItem.skill.id
    }

    override fun areContentsTheSame(oldItem: SkillTree, newItem: SkillTree): Boolean {
        return oldItem == newItem
    }
}