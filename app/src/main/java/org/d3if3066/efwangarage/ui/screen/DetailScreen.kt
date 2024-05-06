package org.d3if3066.efwangarage.ui.screen

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3066.efwangarage.ui.theme.EfwanGarageTheme

@Composable
fun DetailScreen(navController: NavHostController) {

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
   EfwanGarageTheme {
        DetailScreen(rememberNavController())
    }
}