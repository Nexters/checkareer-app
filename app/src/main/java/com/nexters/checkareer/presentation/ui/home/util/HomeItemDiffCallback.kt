package com.nexters.checkareer.presentation.ui.home.util

import androidx.recyclerview.widget.DiffUtil
import com.nexters.checkareer.presentation.ui.home.model.Home

class HomeItemDiffCallback : DiffUtil.ItemCallback<Home>() {

    override fun areItemsTheSame(oldItem: Home, newItem: Home): Boolean {
        return oldItem.viewType == newItem.viewType
    }

    override fun areContentsTheSame(oldItem: Home, newItem: Home): Boolean {
        return oldItem == newItem
    }
}