package com.nexters.checkareer.presentation.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.presentation.ui.home.HomeViewModel
import com.nexters.checkareer.presentation.ui.home.holder.MyProfileViewHolder
import com.nexters.checkareer.presentation.ui.home.holder.OtherProfileViewHolder
import com.nexters.checkareer.presentation.ui.home.listener.HomeListener
import com.nexters.checkareer.presentation.ui.home.model.Home
import com.nexters.checkareer.presentation.ui.home.model.MyProfile
import com.nexters.checkareer.presentation.ui.home.model.OtherProfile
import com.nexters.checkareer.presentation.ui.home.util.HomeItemDiffCallback

class HomeAdapter(
    private val eventListener: HomeListener
) : ListAdapter<Home, RecyclerView.ViewHolder>(HomeItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Home.HomeType.MY_PROFILE.ordinal -> MyProfileViewHolder.from(parent, eventListener)
            else -> {
                OtherProfileViewHolder.from(parent, eventListener)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            when (holder) {
                is MyProfileViewHolder -> holder.bind(item.item as MyProfile)
                is OtherProfileViewHolder -> holder.bind(item.item as OtherProfile)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].viewType.ordinal
    }
}