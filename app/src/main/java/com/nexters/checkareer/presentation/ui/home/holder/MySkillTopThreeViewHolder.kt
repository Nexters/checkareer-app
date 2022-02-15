package com.nexters.checkareer.presentation.ui.home.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.databinding.SkillItemProfileBinding
import com.nexters.checkareer.databinding.SkillItemProfileHomeBinding
import com.nexters.checkareer.databinding.SkillItemVerticalBinding
import com.nexters.checkareer.domain.skill.Skill

class MySkillTopThreeViewHolder constructor(val binding: SkillItemProfileHomeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Skill) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): MySkillTopThreeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SkillItemProfileHomeBinding.inflate(layoutInflater, parent, false)

            return MySkillTopThreeViewHolder(binding)
        }
    }
}