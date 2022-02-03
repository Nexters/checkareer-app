package com.nexters.checkareer.presentation.ui.createprofile.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.databinding.SkillCategoryItemProfileBinding
import com.nexters.checkareer.databinding.SkillCategoryItemSelectedBinding
import com.nexters.checkareer.presentation.ui.createprofile.listener.SkillCategoryListener
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect

class ProfileSkillViewHolder constructor(val binding: SkillCategoryItemProfileBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CategorySelect) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, eventListener: SkillCategoryListener): ProfileSkillViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SkillCategoryItemProfileBinding.inflate(layoutInflater, parent, false)
            binding.eventListener = eventListener

            return ProfileSkillViewHolder(binding)
        }
    }
}