package com.nexters.checkareer.presentation.ui.createprofile.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.nexters.checkareer.presentation.ui.createprofile.adapter.SkillCategoryAdapter
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect
import com.nexters.checkareer.util.fromDpToPx
import android.graphics.Typeface
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.presentation.ui.home.adapter.MySkillAdapter


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

@BindingAdapter("mySkillTopThreeItems")
fun setMySkillTopThreeItems(recyclerView: RecyclerView, items: List<Skill>?) {
    (recyclerView.adapter as? MySkillAdapter)?.run {
        submitList(items?.take(3) ?: listOf())
        notifyDataSetChanged()
    }
}

@BindingAdapter("mySkillItems")
fun setMySkillItems(recyclerView: RecyclerView, items: List<Skill>?) {
    (recyclerView.adapter as? MySkillAdapter)?.run {
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