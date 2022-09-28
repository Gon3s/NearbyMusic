package com.gones.nearbymusic.ui._navigation

import com.gones.nearbymusic.util.Constant.HOME_SCREEN
import com.gones.nearbymusic.util.Constant.LOGIN_SCREEN
import com.gones.nearbymusic.util.Constant.PRESENTATION_SCREEN
import com.gones.nearbymusic.util.Constant.REGISTER_SCREEN

sealed class Screens (
    val route: String
) {
    object Presentation: Screens(PRESENTATION_SCREEN)
    object Login: Screens(LOGIN_SCREEN)
    object Register: Screens(REGISTER_SCREEN)
    object Home: Screens(HOME_SCREEN)
}