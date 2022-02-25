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
import com.nexters.checkareer.domain.vo.Profile
import com.nexters.checkareer.domain.vo.SkillTree
import com.nexters.checkareer.presentation.ui.editprofile.adapter.MySkillEditAdapter
import com.nexters.checkareer.presentation.ui.home.adapter.MySkillAdapter
import com.nexters.checkareer.presentation.ui.home.adapter.ProfileAdapter


@BindingAdapter("skillItems")
fun setSkillItems(recyclerView: RecyclerView, items: List<CategorySelect>?) {
    (recyclerView.adapter as? SkillCategoryAdapter)?.run {
        submitList(items)
    }
}

@BindingAdapter("selectedSkillItems")
fun setSelectedSkillItems(recyclerView: RecyclerView, items: List<CategorySelect>?) {
    (recyclerView.adapter as? SkillCategoryAdapter)?.run {
        submitList(items)
    }
}

@BindingAdapter("mySkillTopThreeItems")
fun setMySkillTopThreeItems(recyclerView: RecyclerView, items: List<Skill>?) {
    (recyclerView.adapter as? MySkillAdapter)?.run {
        submitList(items?.take(3) ?: listOf())
        notifyDataSetChanged()
    }
}

@BindingAdapter("mySkillTreeTopThreeItems")
fun setMySkillTreeTopThreeItems(recyclerView: RecyclerView, items: List<SkillTree>?) {
    (recyclerView.adapter as? MySkillAdapter)?.run {
        submitList(items?.take(3)?.map { it.skill } ?: listOf())
        notifyDataSetChanged()
    }
}

@BindingAdapter("homeMySkills")
fun setHomeMySkills(recyclerView: RecyclerView, items: List<Skill>?) {
    (recyclerView.adapter as? MySkillAdapter)?.run {
        submitList(items)
        notifyDataSetChanged()
    }
}


@BindingAdapter("mySkillItems")
fun setMySkillItems(recyclerView: RecyclerView, items: List<SkillTree>?) {
    (recyclerView.adapter as? MySkillAdapter)?.run {
        submitList(items?.map { it.skill })
        notifyDataSetChanged()
    }
}

@BindingAdapter("mySkillItemsEdit")
fun setMySkillItemsEdit(recyclerView: RecyclerView, items: List<SkillTree>?) {
    (recyclerView.adapter as? MySkillEditAdapter)?.run {
        submitList(items?.map { it.skill })
        notifyDataSetChanged()
    }
}

@BindingAdapter("friendProfileItems")
fun setFriendProfileItems(recyclerView: RecyclerView, items: List<Profile>?) {
    println(items.toString())
    (recyclerView.adapter as? ProfileAdapter)?.run {
        println(items.toString())
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