package com.nexters.checkareer.presentation.ui.createprofile.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.databinding.SkillCategoryItemSelectedBinding
import com.nexters.checkareer.presentation.ui.createprofile.listener.SkillCategoryListener
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect

class SelectedSkillViewHolder constructor(val binding: SkillCategoryItemSelectedBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CategorySelect) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, eventListener: SkillCategoryListener): SelectedSkillViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SkillCategoryItemSelectedBinding.inflate(layoutInflater, parent, false)
            binding.eventListener = eventListener

            return SelectedSkillViewHolder(binding)
        }
    }
}