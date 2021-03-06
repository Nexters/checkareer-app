package com.nexters.checkareer.presentation.ui.editprofile.holder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.nexters.checkareer.databinding.SkillItemVerticalEditBinding
import com.nexters.checkareer.domain.vo.SkillTree
import com.nexters.checkareer.presentation.ui.editprofile.listener.SkillEditListener
import com.nexters.checkareer.presentation.ui.editprofile.listener.ItemDragListener
import com.nexters.checkareer.presentation.ui.home.adapter.skill.ChildSkillAdapter

class MySkillEditViewHolder constructor(val binding: SkillItemVerticalEditBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("ClickableViewAccessibility")
    fun bind(item: SkillTree) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        @SuppressLint("ClickableViewAccessibility")
        fun from(parent: ViewGroup, eventListener: SkillEditListener): MySkillEditViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SkillItemVerticalEditBinding.inflate(layoutInflater, parent, false)
            val viewholder = MySkillEditViewHolder(binding)
            binding.eventListener = eventListener



            binding.detailSkillRecyclerView.apply {
                val layoutManager = FlexboxLayoutManager(parent.context)
                layoutManager.apply {
                    flexDirection = FlexDirection.ROW
                    justifyContent = JustifyContent.FLEX_START
                }
                setLayoutManager(layoutManager)
                adapter = ChildSkillAdapter()
            }

            return viewholder
        }
    }
}