package com.nexters.checkareer.presentation.ui.editprofile.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.vo.SkillTree
import com.nexters.checkareer.presentation.ui.editprofile.holder.MySkillEditOrderViewHolder
import com.nexters.checkareer.presentation.ui.editprofile.holder.MySkillEditViewHolder
import com.nexters.checkareer.presentation.ui.editprofile.listener.SkillEditListener
import com.nexters.checkareer.presentation.ui.editprofile.listener.ItemActionListener
import com.nexters.checkareer.presentation.ui.editprofile.listener.ItemDragListener
import com.nexters.checkareer.presentation.ui.home.util.SkillTreeItemDiffCallback

class MySkillEditOrderAdapter(
    private val type: String,
    private val dragListener: ItemDragListener,
    private val itemMove: () -> Unit
) : ListAdapter<SkillTree, RecyclerView.ViewHolder>(SkillTreeItemDiffCallback()), ItemActionListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (type) {
            "SKILL_LIST" -> MySkillEditOrderViewHolder.from(parent, dragListener)
            else -> MySkillEditOrderViewHolder.from(parent, dragListener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            when (holder) {
                is MySkillEditOrderViewHolder -> holder.bind(item)
            }
        }
    }

    override fun onItemMoved(from: Int, to: Int) {
        if(from == to) {
            return
        }
        itemMove()
        val items = this.currentList.toMutableList()
        val fromItem = items.removeAt(from)
        items.add(to, fromItem)
        notifyItemMoved(from, to)
    }

}



