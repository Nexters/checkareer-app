package com.nexters.checkareer.presentation.ui.editprofile.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.databinding.SkillItemVerticalEditBinding
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.presentation.ui.editprofile.listener.SkillEditListener

class MySkillEditViewHolder constructor(val binding: SkillItemVerticalEditBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Skill) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, eventListener: SkillEditListener): MySkillEditViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SkillItemVerticalEditBinding.inflate(layoutInflater, parent, false)
            binding.eventListener = eventListener

            return MySkillEditViewHolder(binding)
        }
    }
}