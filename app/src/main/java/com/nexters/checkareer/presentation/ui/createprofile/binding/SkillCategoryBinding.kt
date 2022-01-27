package com.nexters.checkareer.presentation.ui.createprofile.binding

import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.checkareer.domain.category.Category
import com.nexters.checkareer.presentation.ui.createprofile.adapter.SkillCategoryAdapter
import com.nexters.checkareer.presentation.ui.createprofile.model.CategorySelect

@BindingAdapter("skillItems")
fun setSkillItems(recyclerView: RecyclerView, items: List<Category>?) {
    (recyclerView.adapter as? SkillCategoryAdapter)?.run {
        submitList(items?.map { CategorySelect(it.id, it.name) })
    }
}
