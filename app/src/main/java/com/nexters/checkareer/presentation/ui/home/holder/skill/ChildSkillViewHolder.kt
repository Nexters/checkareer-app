package com.nexters.checkareer.presentation.ui.home.holder.skill

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.databinding.HomeChildSkillItemBinding
import com.nexters.checkareer.domain.skill.Skill

class ChildSkillViewHolder constructor(val binding: HomeChildSkillItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Skill) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ChildSkillViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = HomeChildSkillItemBinding.inflate(layoutInflater, parent, false)

            return ChildSkillViewHolder(binding)
        }
    }
}