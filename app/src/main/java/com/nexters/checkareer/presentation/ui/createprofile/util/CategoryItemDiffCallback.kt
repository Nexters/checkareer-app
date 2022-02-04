package com.nexters.checkareer.presentation.ui.createprofile.util

import androidx.recyclerview.widget.DiffUtil
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect

class CategoryItemDiffCallback : DiffUtil.ItemCallback<CategorySelect>() {

    override fun areItemsTheSame(oldItem: CategorySelect, newItem: CategorySelect): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CategorySelect, newItem: CategorySelect): Boolean {
        return oldItem == newItem
    }
}