package com.nexters.checkareer.presentation.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.domain.vo.Profile
import com.nexters.checkareer.presentation.ui.home.holder.FriendProfileViewHolder
import com.nexters.checkareer.presentation.ui.home.listener.FriendProfileListener
import com.nexters.checkareer.presentation.ui.home.util.ProfileItemDiffCallback

class ProfileAdapter(
    private val eventListener: FriendProfileListener
) : ListAdapter<Profile, RecyclerView.ViewHolder>(ProfileItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FriendProfileViewHolder.from(parent, eventListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        getItem(position)?.let { item ->

            when (holder) {
                is FriendProfileViewHolder -> holder.bind(item)
            }
        }
    }
}



