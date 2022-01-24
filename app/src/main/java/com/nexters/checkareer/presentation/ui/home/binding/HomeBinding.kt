package com.nexters.checkareer.presentation.ui.home.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.presentation.ui.home.adapter.HomeAdapter
import com.nexters.checkareer.presentation.ui.home.model.Home


@BindingAdapter("homeItems")
fun setHomeItems(recyclerView: RecyclerView, items: List<Home>?) {
    (recyclerView.adapter as? HomeAdapter)?.run {
        submitList(items)
    }
}