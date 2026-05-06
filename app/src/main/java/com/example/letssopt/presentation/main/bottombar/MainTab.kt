package com.example.letssopt.presentation.main.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.letssopt.R
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.archive.navigation.ArchiveRoute
import com.example.letssopt.presentation.home.navigation.HomeRoute
import com.example.letssopt.presentation.purchase.navigation.PurchaseRoute
import com.example.letssopt.presentation.search.navigation.SearchRoute
import com.example.letssopt.presentation.webtoon.navigation.WebtoonRoute

enum class MainTab(
    @param:StringRes val labelRes: Int,
    @param:DrawableRes val iconRes: Int,
    val route: Route,
) {
    HOME(
        labelRes = R.string.bottombar_main,
        iconRes = R.drawable.ic_bottom_bar_main_24,
        route = HomeRoute.Home,
    ),
    PURCHASE(
        labelRes = R.string.bottombar_purchase,
        iconRes = R.drawable.ic_bottom_bar_category_24,
        route = PurchaseRoute.Purchase,
    ),
    WEBTOON(
        labelRes = R.string.bottombar_webtoon,
        iconRes = R.drawable.ic_bottom_bar_wallet_24,
        route = WebtoonRoute.Webtoon,
    ),
    SEARCH(
        labelRes = R.string.bottombar_search,
        iconRes = R.drawable.ic_bottom_bar_search_24,
        route = SearchRoute.Search,
    ),
    ARCHIVE(
        labelRes = R.string.bottombar_archive,
        iconRes = R.drawable.ic_bottom_bar_folder_24,
        route = ArchiveRoute.Archive,
    )
}
