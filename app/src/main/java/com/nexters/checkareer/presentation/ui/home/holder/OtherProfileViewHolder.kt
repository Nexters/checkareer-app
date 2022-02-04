package com.nexters.checkareer.presentation.ui.home.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.databinding.HomeOtherProfileItemBinding
import com.nexters.checkareer.presentation.ui.home.listener.HomeListener
import com.nexters.checkareer.presentation.ui.home.model.OtherProfile

class OtherProfileViewHolder constructor(val binding: HomeOtherProfileItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: OtherProfile) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, eventListener: HomeListener): OtherProfileViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = HomeOtherProfileItemBinding.inflate(layoutInflater, parent, false)
            binding.eventListener = eventListener

            return OtherProfileViewHolder(binding)
        }
    }
}