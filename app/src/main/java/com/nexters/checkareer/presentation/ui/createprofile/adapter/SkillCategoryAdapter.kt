package com.nexters.checkareer.presentation.ui.createprofile.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.presentation.ui.createprofile.holder.SkillViewHolder
import com.nexters.checkareer.presentation.ui.createprofile.listener.SkillCategoryListener
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import com.nexters.checkareer.presentation.ui.createprofile.util.CategoryItemDiffCallback

class SkillCategoryAdapter(
    private val eventListener: SkillCategoryListener
) : ListAdapter<CategorySelect, RecyclerView.ViewHolder>(CategoryItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SkillViewHolder.from(parent, eventListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            when (holder) {
                is SkillViewHolder -> holder.bind(item)
            }
        }
    }
}



