package com.nexters.checkareer.presentation.ui.editprofile.listener

import androidx.recyclerview.widget.RecyclerView

interface ItemDragListener {

    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)

    fun onEndDrag(viewHolder: RecyclerView.ViewHolder)

}