package eu.tutorials.musicappui

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

sealed class Screen(val title: String, val route: String) {



    //Bottom screen bar
    sealed class BottomScreen(
        val bTitle: String, val bRoute: String, @DrawableRes val icon: Int
    ) : Screen(bTitle, bRoute){
        object Home: BottomScreen(
            "Home",
            "home",
            R.drawable.outline_home_24
        )
        object Browse: BottomScreen(
            "Browse",
            "browse",
            R.drawable.outline_category_search_24
        )
        object Library: BottomScreen(
            "Library",
            "library",
            R.drawable.outline_library_music_24
        )
    }



    //Drawer screen bar
    sealed class DrawerScreen(
        val dTitle: String, val dRoute: String, @DrawableRes val icon: Int
    ) : Screen(dTitle, dRoute){
        object Account: DrawerScreen(
            "Account",
            "account",
            R.drawable.ic_account,
        )
        object Subscription: DrawerScreen(
            "Subscription",
            "subscribe",
            R.drawable.ic_subscribe
        )
        object AddAccount: DrawerScreen(
            "Add Account",
            "add_account",
            R.drawable.ic_add_account
        )
    }
}




val screensInBottom = listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.Browse,
    Screen.BottomScreen.Library
)

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount
)

