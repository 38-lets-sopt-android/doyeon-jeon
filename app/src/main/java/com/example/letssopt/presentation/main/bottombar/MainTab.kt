package com.example.letssopt.presentation.main.bottombar

import androidx.annotation.DrawableRes
import com.example.letssopt.R

enum class MainTab (
    val label: String,
    @param:DrawableRes val iconRes: Int,
) {
    MAIN(
        label = "메인",
        iconRes = R.drawable.ic_bottom_bar_main_24,
    ),
    PURCHASE(
        label = "개별 구매",
        iconRes = R.drawable.ic_bottom_bar_category_24,
    ),
    WEBTOON(
        label = "웹툰",
        iconRes = R.drawable.ic_bottom_bar_wallet_24,
    ),
    SEARCH(
        label = "찾기",
        iconRes = R.drawable.ic_bottom_bar_search_24,
    ),
    ARCHIVE(
        label = "보관함",
        iconRes = R.drawable.ic_bottom_bar_folder_24,
    )
}
