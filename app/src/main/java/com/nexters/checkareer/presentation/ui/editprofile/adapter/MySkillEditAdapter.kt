package com.nexters.checkareer.presentation.ui.editprofile.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.vo.SkillTree
import com.nexters.checkareer.presentation.ui.editprofile.holder.MySkillEditViewHolder
import com.nexters.checkareer.presentation.ui.editprofile.listener.SkillEditListener
import com.nexters.checkareer.presentation.ui.editprofile.listener.ItemActionListener
import com.nexters.checkareer.presentation.ui.editprofile.listener.ItemDragListener
import com.nexters.checkareer.presentation.ui.home.util.SkillTreeItemDiffCallback

class MySkillEditAdapter(
    private val type: String,
    private val eventListener: SkillEditListener
) : ListAdapter<SkillTree, RecyclerView.ViewHolder>(SkillTreeItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (type) {
            "SKILL_LIST" -> MySkillEditViewHolder.from(parent, eventListener)
            else -> MySkillEditViewHolder.from(parent, eventListener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            when (holder) {
                is MySkillEditViewHolder -> holder.bind(item)
            }
        }
    }
}



