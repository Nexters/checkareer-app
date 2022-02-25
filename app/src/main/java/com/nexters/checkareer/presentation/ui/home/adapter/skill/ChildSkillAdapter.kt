package com.nexters.checkareer.presentation.ui.home.adapter.skill

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.presentation.ui.home.holder.skill.ChildSkillViewHolder
import com.nexters.checkareer.presentation.ui.home.holder.skill.SkillTreeViewHolder
import com.nexters.checkareer.presentation.ui.home.util.SkillItemDiffCallback

class ChildSkillAdapter(
    ) : ListAdapter<Skill, RecyclerView.ViewHolder>(SkillItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ChildSkillViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            when (holder) {
                is ChildSkillViewHolder -> holder.bind(item)
            }
        }
    }
}

