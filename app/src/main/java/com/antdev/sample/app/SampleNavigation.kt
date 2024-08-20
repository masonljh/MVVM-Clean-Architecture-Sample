package com.antdev.sample.app

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.antdev.sample.app.SampleDestinationsArgs.ITEM_ID_ARG
import com.antdev.sample.app.SampleScreens.ADD_SCREEN
import com.antdev.sample.app.SampleScreens.DETAIL_SCREEN
import com.antdev.sample.app.SampleScreens.MAIN_SCREEN
import com.antdev.sample.app.SampleScreens.MESSAGES_SCREEN

private object SampleScreens {
    const val MAIN_SCREEN = "main"
    const val DETAIL_SCREEN = "detail"
    const val ADD_SCREEN = "add"
    const val MESSAGES_SCREEN = "messages"
}

object SampleDestinationsArgs {
    const val ITEM_ID_ARG = "itemId"
    const val TITLE_ARG = "title"
}

object SampleDestinations {
    const val MAIN_ROUTE = MAIN_SCREEN
    const val DETAIL_ROUTE = "$DETAIL_SCREEN/{$ITEM_ID_ARG}"
    const val ADD_ROUTE = ADD_SCREEN
    const val MESSAGES_ROUTE = MESSAGES_SCREEN
}

class SampleNavigationActions(private val navController: NavHostController) {

    fun navigateToMain() {
        navController.navigate(MAIN_SCREEN) {
            launchSingleTop = true
            restoreState = false
        }
    }

}