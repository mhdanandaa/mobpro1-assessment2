package org.d3if3066.efwangarage.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import org.d3if3066.efwangarage.R
import org.d3if3066.efwangarage.ui.theme.EfwanGarageTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    //Untuk Backround
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    //Untuk Judul
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))

    }

}

@Composable
fun ScreenContent(padding: Modifier) {

}
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    EfwanGarageTheme {
        MainScreen(rememberNavController())
    }
}