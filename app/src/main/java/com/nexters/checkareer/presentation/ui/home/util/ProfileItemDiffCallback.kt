package com.nexters.checkareer.presentation.ui.home.util

import androidx.recyclerview.widget.DiffUtil
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.vo.Profile
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect

class ProfileItemDiffCallback : DiffUtil.ItemCallback<Profile>() {

    override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean {
        return oldItem == newItem
    }
}