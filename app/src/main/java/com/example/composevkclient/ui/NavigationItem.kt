package com.example.composevkclient.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.composevkclient.R

sealed class NavigationItem (
    val titleResId: Int,
    val icon: ImageVector
) {
    data object Home: NavigationItem(
        titleResId = R.string.navigation_item_main,
        icon = Icons.Outlined.Home
    )
    data object Favourites: NavigationItem(
        titleResId = R.string.navigation_item_favourites,
        icon = Icons.Outlined.Favorite
    )
    data object Profile: NavigationItem(
        titleResId = R.string.navigation_item_profile,
        icon = Icons.Outlined.Person
    )
}