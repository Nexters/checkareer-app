package com.nexters.checkareer.presentation.ui.editprofile.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

import com.nexters.checkareer.presentation.ui.editprofile.listener.ItemActionListener


class ItemTouchHelperCallback(val listener: ItemActionListener) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.DOWN or ItemTouchHelper.UP
        val swipeFlags = 0
        return makeMovementFlags(dragFlags, swipeFlags)
    }


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        listener.onItemMoved(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    }

    override fun isLongPressDragEnabled(): Boolean = false
}