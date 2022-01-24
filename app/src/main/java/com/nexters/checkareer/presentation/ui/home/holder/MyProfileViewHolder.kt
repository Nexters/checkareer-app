package com.nexters.checkareer.presentation.ui.home.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.databinding.HomeMyProfileItemBinding
import com.nexters.checkareer.presentation.ui.home.listener.HomeListener
import com.nexters.checkareer.presentation.ui.home.model.MyProfile

class MyProfileViewHolder constructor(val binding: HomeMyProfileItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MyProfile) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, eventListener: HomeListener): MyProfileViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = HomeMyProfileItemBinding.inflate(layoutInflater, parent, false)
            binding.eventListener = eventListener

            return MyProfileViewHolder(binding)
        }
    }
}