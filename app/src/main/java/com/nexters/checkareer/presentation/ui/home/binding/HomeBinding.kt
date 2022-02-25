package com.nexters.checkareer.presentation.ui.home.binding

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.nexters.checkareer.R
import com.nexters.checkareer.domain.skill.Skill
import com.nexters.checkareer.domain.user.User
import com.nexters.checkareer.domain.vo.SkillTree
import com.nexters.checkareer.presentation.ui.home.adapter.HomeAdapter
import com.nexters.checkareer.presentation.ui.home.adapter.MySkillAdapter
import com.nexters.checkareer.presentation.ui.home.adapter.skill.ChildSkillAdapter
import com.nexters.checkareer.presentation.ui.home.adapter.skill.SkillTreeAdapter
import com.nexters.checkareer.presentation.ui.home.model.Home


@BindingAdapter("homeItems")
fun setHomeItems(recyclerView: RecyclerView, items: List<Home>?) {
    (recyclerView.adapter as? HomeAdapter)?.run {
        submitList(items)
    }
}

@BindingAdapter("skillTreeItems")
fun setSkillTreeItems(recyclerView: RecyclerView, items: List<SkillTree>?) {
    (recyclerView.adapter as? SkillTreeAdapter)?.run {
        submitList(items)
    }
}

@BindingAdapter("childSkillItems")
fun setChildSkillItems(recyclerView: RecyclerView, items: List<Skill>?) {
    (recyclerView.adapter as? ChildSkillAdapter)?.run {
        submitList(items)
    }
}

@BindingAdapter("imageUrl")
fun loadUserImage(imageView: ImageView, user: User?) {
    user?.let {
        Glide.with(imageView.context)
            .load(it.logInInfo?.photoUrl?.toUri())
            .placeholder(R.mipmap.ic_launcher_round)
            .error(Glide.with(imageView.context).load(R.mipmap.ic_launcher_round).centerCrop())
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .transition(DrawableTransitionOptions.withCrossFade()).into(imageView)
    }
}

private fun loadImage(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView.context)
        .load(imageUrl?.toUri())
        .placeholder(R.mipmap.ic_launcher_round)
        .error(Glide.with(imageView.context).load(R.mipmap.ic_launcher_round).centerCrop())
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .transition(DrawableTransitionOptions.withCrossFade()).into(imageView)
}