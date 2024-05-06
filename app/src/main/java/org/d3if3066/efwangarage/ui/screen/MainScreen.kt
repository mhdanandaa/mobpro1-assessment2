package org.d3if3066.efwangarage.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.d3if3066.efwangarage.ui.theme.EfwanGarageTheme

@Composable
fun MainScreen(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    EfwanGarageTheme {
        MainScreen(name = "Test")
    }
}