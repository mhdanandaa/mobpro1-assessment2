package org.d3if3066.efwangarage.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3066.efwangarage.ui.theme.EfwanGarageTheme

@Composable
fun MainScreen(navController: NavHostController) {

}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    EfwanGarageTheme {
        MainScreen(rememberNavController())
    }
}