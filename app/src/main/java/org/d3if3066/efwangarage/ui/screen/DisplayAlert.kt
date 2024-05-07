package org.d3if3066.efwangarage.ui.screen

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.d3if3066.efwangarage.R
import org.d3if3066.efwangarage.ui.theme.EfwanGarageTheme

@Composable
fun DisplayAlert(
    openDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if(openDialog) {
        AlertDialog(
            text = { Text(text = stringResource(R.string.hapus_pesan))},

            confirmButton = {
                TextButton(onClick = {onConfirm ()}) {
                    Text(text = stringResource(R.string.tombol_hapus))
                }
            },
            dismissButton = {
                TextButton(onClick = {onDismiss ()}) {
                    Text(text = stringResource(R.string.tombol_batal))
                }
            },
            onDismissRequest = {onDismiss()},

        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DialogPreview() {
    EfwanGarageTheme {
        DisplayAlert(
            openDialog = true,
            onDismiss = {},
            onConfirm = {}
        )
    }
}