package com.nexters.checkareer.presentation.ui.editprofile.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.databinding.SkillItemVerticalEditOrderBinding
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.vo.SkillTree
import com.nexters.checkareer.presentation.ui.editprofile.holder.MySkillEditOrderViewHolder
import com.nexters.checkareer.presentation.ui.editprofile.holder.MySkillEditViewHolder
import com.nexters.checkareer.presentation.ui.editprofile.listener.SkillEditListener
import com.nexters.checkareer.presentation.ui.editprofile.listener.ItemActionListener
import com.nexters.checkareer.presentation.ui.editprofile.listener.ItemDragListener
import com.nexters.checkareer.presentation.ui.home.util.SkillTreeItemDiffCallback

class MySkillEditOrderAdapter(
    val items: MutableList<SkillTree>,
    private val dragListener: ItemDragListener,
    private val itemMove: (List<SkillTree>) -> Unit
) : ListAdapter<SkillTree, MySkillEditOrderAdapter.ViewHolder>(SkillTreeItemDiffCallback()), ItemActionListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SkillItemVerticalEditOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        //getItem(position)?.let { item -> holder.bind(item) }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onItemMoved(from: Int, to: Int) {
        if(from == to) {
            return
        }
        val fromItem = items.removeAt(from)
        items.add(to, fromItem)
        notifyItemMoved(from, to)
        itemMove(items)
    }

    inner class ViewHolder(private val binding: SkillItemVerticalEditOrderBinding): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("ClickableViewAccessibility")
        fun bind(skill: SkillTree) = with(binding) {

            imageviewDrag.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    dragListener.onStartDrag(this@ViewHolder)
                }
                false
            }
            textviewSkillName.text = skill.skill.name
        }

    }


}



