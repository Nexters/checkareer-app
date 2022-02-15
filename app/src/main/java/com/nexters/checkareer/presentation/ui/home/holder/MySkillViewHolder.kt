package com.nexters.checkareer.presentation.ui.home.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.databinding.SkillItemVerticalBinding
import com.nexters.checkareer.domain.skill.Skill

class MySkillViewHolder constructor(val binding: SkillItemVerticalBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Skill) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): MySkillViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SkillItemVerticalBinding.inflate(layoutInflater, parent, false)

            return MySkillViewHolder(binding)
        }
    }
}