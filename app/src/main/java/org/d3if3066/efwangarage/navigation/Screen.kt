package org.d3if3066.efwangarage.navigation

sealed class Screen (val route: String) {

    data object Home: Screen("mainScreen")

    data object FormBaru: Screen("detailScreen")
}