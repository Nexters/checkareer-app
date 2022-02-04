package com.nexters.checkareer.presentation.ui.home.model

data class Home(
    val viewType: HomeType,
    val item: HomeInfo
) {
    enum class HomeType {
        MY_PROFILE,
        OTHER_PROFILE
    }
}
