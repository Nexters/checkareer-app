package com.nexters.checkareer.presentation.ui.home.adapter.skill

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.domain.vo.SkillTree
import com.nexters.checkareer.presentation.ui.home.holder.skill.SkillTreeViewHolder
import com.nexters.checkareer.presentation.ui.home.util.SkillTreeItemDiffCallback

class SkillTreeAdapter(
    private val type: SkillTreeViewType
    ) : ListAdapter<SkillTree, RecyclerView.ViewHolder>(SkillTreeItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (type) {
            SkillTreeViewType.HOME -> SkillTreeViewHolder.from(parent)
            else -> SkillTreeViewHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            when (holder) {
                is SkillTreeViewHolder -> holder.bind(item)
            }
        }
    }
}

enum class SkillTreeViewType{
    HOME,
    EDIT
}


