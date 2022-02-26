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
import com.nexters.checkareer.databinding.SkillItemVerticalEditOrderBinding
import com.nexters.checkareer.domain.vo.SkillTree
import com.nexters.checkareer.presentation.ui.editprofile.listener.SkillEditListener
import com.nexters.checkareer.presentation.ui.editprofile.listener.ItemDragListener
import com.nexters.checkareer.presentation.ui.home.adapter.skill.ChildSkillAdapter

class MySkillEditOrderViewHolder constructor(val binding: SkillItemVerticalEditOrderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("ClickableViewAccessibility")
    fun bind(item: SkillTree) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        @SuppressLint("ClickableViewAccessibility")
        fun from(parent: ViewGroup, listener: ItemDragListener): MySkillEditOrderViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SkillItemVerticalEditOrderBinding.inflate(layoutInflater, parent, false)
            val viewholder = MySkillEditOrderViewHolder(binding)

            binding.imageviewDrag.setOnTouchListener { v, event ->
                if(event.action == MotionEvent.ACTION_DOWN) {
                    listener.onStartDrag(viewholder)
                }
                false
            }

            return viewholder
        }
    }
}