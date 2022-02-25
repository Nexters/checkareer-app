package com.nexters.checkareer.presentation.ui.home.holder.skill

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.nexters.checkareer.databinding.HomeSkillTreeItemBinding
import com.nexters.checkareer.domain.vo.SkillTree
import com.nexters.checkareer.presentation.ui.home.adapter.skill.ChildSkillAdapter

class SkillTreeViewHolder constructor(val binding: HomeSkillTreeItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SkillTree) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): SkillTreeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = HomeSkillTreeItemBinding.inflate(layoutInflater, parent, false)
            binding.detailSkillRecyclerView.apply {
                val layoutManager = FlexboxLayoutManager(parent.context)
                layoutManager.apply {
                    flexDirection = FlexDirection.ROW
                    justifyContent = JustifyContent.FLEX_START
                }
                setLayoutManager(layoutManager)
                adapter = ChildSkillAdapter()
            }

            return SkillTreeViewHolder(binding)
        }
    }
}