package com.nexters.checkareer.presentation.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.presentation.ui.home.holder.MySkillTopThreeViewHolder
import com.nexters.checkareer.presentation.ui.home.holder.MySkillViewHolder
import com.nexters.checkareer.presentation.ui.home.util.SkillItemDiffCallback

class MySkillAdapter(
    private val type: String
    ) : ListAdapter<Skill, RecyclerView.ViewHolder>(SkillItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (type) {
            "SKILL_LIST" -> MySkillViewHolder.from(parent)
            "SKILL_TOP_THREE_LIST" -> MySkillTopThreeViewHolder.from(parent)
            else -> MySkillViewHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            when (holder) {
                is MySkillViewHolder -> holder.bind(item)
                is MySkillTopThreeViewHolder -> holder.bind(item)
            }
        }
    }
}



