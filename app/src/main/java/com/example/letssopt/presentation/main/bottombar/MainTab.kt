package com.example.letssopt.presentation.main.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.letssopt.R

enum class MainTab (
    @param:StringRes val labelRes: Int,
    @param:DrawableRes val iconRes: Int,
) {
    HOME(
        labelRes = R.string.bottombar_main,
        iconRes = R.drawable.ic_bottom_bar_main_24,
    ),
    PURCHASE(
        labelRes = R.string.bottombar_purchase,
        iconRes = R.drawable.ic_bottom_bar_category_24,
    ),
    WEBTOON(
        labelRes = R.string.bottombar_webtoon,
        iconRes = R.drawable.ic_bottom_bar_wallet_24,
    ),
    SEARCH(
        labelRes = R.string.bottombar_search,
        iconRes = R.drawable.ic_bottom_bar_search_24,
    ),
    ARCHIVE(
        labelRes = R.string.bottombar_archive,
        iconRes = R.drawable.ic_bottom_bar_folder_24,
    )
}
