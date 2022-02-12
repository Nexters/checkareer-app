package com.nexters.checkareer.presentation.ui.createprofile.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.databinding.SkillCategoryItemBinding
import com.nexters.checkareer.presentation.ui.createprofile.listener.SkillCategoryListener
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect

class SkillViewHolder constructor(val binding: SkillCategoryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CategorySelect) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, eventListener: SkillCategoryListener): SkillViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SkillCategoryItemBinding.inflate(layoutInflater, parent, false)
            binding.eventListener = eventListener

            return SkillViewHolder(binding)
        }
    }
}