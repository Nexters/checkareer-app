package com.nexters.checkareer.presentation.ui.createprofile.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CategorySelect(
    val id: String,
    val name: String,
    val parentId: Int?,
    val layer: Int,
    var selected: Boolean = false
): Parcelable
