package org.d3if3066.efwangarage.navigation

import org.d3if3066.efwangarage.ui.screen.KEY_ID_CAR

sealed class Screen (val route: String) {

    data object Home: Screen("mainScreen")

    data object FormBaru: Screen("detailScreen")

    data object FormUbah: Screen("detailScreen/{$KEY_ID_CAR}") {
        fun withId(id: Long) = "detailScreen/$id"
    }
}