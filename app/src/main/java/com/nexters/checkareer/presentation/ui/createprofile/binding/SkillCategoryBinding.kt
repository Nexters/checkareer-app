package com.nexters.checkareer.presentation.ui.createprofile.binding

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.nexters.checkareer.domain.category.Category
import com.nexters.checkareer.presentation.ui.createprofile.adapter.SkillCategoryAdapter
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import com.nexters.checkareer.util.fromDpToPx
import android.graphics.Typeface

import android.R.style




@BindingAdapter("skillItems")
fun setSkillItems(recyclerView: RecyclerView, items: List<CategorySelect>?) {
    (recyclerView.adapter as? SkillCategoryAdapter)?.run {
        submitList(items)
        notifyDataSetChanged()
    }
}

@BindingAdapter("selectedSkillItems")
fun setSelectedSkillItems(recyclerView: RecyclerView, items: List<CategorySelect>?) {
    (recyclerView.adapter as? SkillCategoryAdapter)?.run {
        submitList(items)
        notifyDataSetChanged()
    }
}


@BindingAdapter("android:strokeWidth")
fun setStrokeWidth(view: MaterialCardView, selected: Boolean) {
    view.strokeWidth = if(selected) 2F.fromDpToPx() else 1F.fromDpToPx()
}

@BindingAdapter("android:skillTextStyle")
fun setTypeFace(textView: TextView, style: String) {
    when (style) {
        "bold" -> textView.setTypeface(null, Typeface.BOLD)
        "normal" -> textView.setTypeface(null, Typeface.NORMAL)
    }
}