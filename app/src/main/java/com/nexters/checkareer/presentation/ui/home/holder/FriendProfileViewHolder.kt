package com.nexters.checkareer.presentation.ui.home.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.databinding.HomeProfileItemBinding
import com.nexters.checkareer.domain.vo.Profile
import com.nexters.checkareer.presentation.ui.home.listener.FriendProfileListener

class FriendProfileViewHolder constructor(val binding: HomeProfileItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Profile) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, eventListener: FriendProfileListener): FriendProfileViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = HomeProfileItemBinding.inflate(layoutInflater, parent, false)
            binding.eventListener = eventListener

            return FriendProfileViewHolder(binding)
        }
    }
}