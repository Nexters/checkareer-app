package com.nexters.checkareer.presentation.ui.createprofile.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.presentation.ui.createprofile.holder.ProfileSkillViewHolder
import com.nexters.checkareer.presentation.ui.createprofile.holder.SelectedSkillViewHolder
import com.nexters.checkareer.presentation.ui.createprofile.holder.SkillViewHolder
import com.nexters.checkareer.presentation.ui.createprofile.listener.SkillCategoryListener
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import com.nexters.checkareer.presentation.ui.createprofile.util.CategoryItemDiffCallback

class SkillCategoryAdapter(
    private val eventListener: SkillCategoryListener,
    private val type: String
) : ListAdapter<CategorySelect, RecyclerView.ViewHolder>(CategoryItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(type) {
            "SELECTED_SKILL_LIST" -> SelectedSkillViewHolder.from(parent, eventListener)
            "PROFILE_SELECTED_SKILL_LIST" -> ProfileSkillViewHolder.from(parent, eventListener)
            else -> SkillViewHolder.from(parent, eventListener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            when (holder) {
                is SelectedSkillViewHolder -> holder.bind(item)
                is ProfileSkillViewHolder -> holder.bind(item)
                is SkillViewHolder -> holder.bind(item)

            }
        }
    }
}



