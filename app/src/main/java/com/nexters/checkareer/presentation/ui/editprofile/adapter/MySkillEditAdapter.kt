package com.nexters.checkareer.presentation.ui.editprofile.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.presentation.ui.editprofile.holder.MySkillEditViewHolder
import com.nexters.checkareer.presentation.ui.editprofile.listener.SkillEditListener
import com.nexters.checkareer.presentation.ui.home.holder.MySkillTopThreeViewHolder
import com.nexters.checkareer.presentation.ui.home.util.SkillItemDiffCallback

class MySkillEditAdapter(
    private val type: String,
    private val eventListener: SkillEditListener
    ) : ListAdapter<Skill, RecyclerView.ViewHolder>(SkillItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (type) {
            "SKILL_LIST" -> MySkillEditViewHolder.from(parent, eventListener)
            "SKILL_TOP_THREE_LIST" -> MySkillTopThreeViewHolder.from(parent)
            else -> MySkillEditViewHolder.from(parent, eventListener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            when (holder) {
                is MySkillEditViewHolder -> holder.bind(item)
                is MySkillTopThreeViewHolder -> holder.bind(item)

            }
        }
    }
}



