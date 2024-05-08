package org.d3if3066.efwangarage.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3066.efwangarage.R
import org.d3if3066.efwangarage.database.GarageDb
import org.d3if3066.efwangarage.ui.theme.EfwanGarageTheme
import org.d3if3066.efwangarage.ui.theme.Purple80
import org.d3if3066.efwangarage.util.ViewModelFactory

const val KEY_ID_CAR = "idGarage"

val radioOptions = listOf(
    "Tersedia",
    "Dipinjam"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null) {
    val context = LocalContext.current
    val db = GarageDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: DetailViewModel = viewModel(factory = factory)

    var merkMobil by remember { mutableStateOf("") }
    var jenisMobil by remember { mutableStateOf("") }
    var warnaMobil by remember { mutableStateOf("") }
    var tahuKeluaran by remember { mutableStateOf("") }
    var status by remember { mutableStateOf(radioOptions[0]) }

    var showDialog by remember{ mutableStateOf(false) }

    LaunchedEffect(true) {
        if(id == null)
            return@LaunchedEffect
        val data = viewModel.getCar(id) ?: return@LaunchedEffect
        merkMobil = data.merkMobil
        jenisMobil = data.jenisMobil
        warnaMobil = data.warnaMobil
        tahuKeluaran = data.tahunKeluaran
        status = data.status
    }


    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack() })
                    {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.kembali),
                            tint = Color.White
                        )
                    }
                },
                title = {
                    if(id == null) {
                        Text(text = stringResource(id = R.string.tambah_car))
                    }
                    else {
                        Text(text = stringResource(id = R.string.edit_car))
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Purple80,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {
                        if(merkMobil == "" || jenisMobil == "" || warnaMobil == "" || tahuKeluaran == "" || status == "") {
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                            return@IconButton
                        } else if(merkMobil.isDigitsOnly()){
                            Toast.makeText(context, R.string.invalid_merk, Toast.LENGTH_LONG).show()
                            return@IconButton
                        } else if(tahuKeluaran == "0") {
                            Toast.makeText(context, R.string.tahun_nol, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }

                        if(id == null) {
                            viewModel.insert(merkMobil,jenisMobil,warnaMobil,tahuKeluaran,status)
                        } else {
                            viewModel.update(id, merkMobil, jenisMobil, warnaMobil, tahuKeluaran, status)

                        }
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(id = R.string.simpan),
                            tint = Color.White
                        )
                    }
                    if(id != null) {
                        DeleteData {
                            showDialog = true
                        }

                        DisplayAlert(
                            openDialog = showDialog,
                            onDismiss = { showDialog = false }
                        ) {
                            showDialog = false
                            viewModel.delete(id)
                            navController.popBackStack()
                        }
                    }
                }
            )
        }
    ) {
        padding ->
        FormGarage(
            merkMobil = merkMobil,
            onMerkChange = {merkMobil = it},
            jenisMobil = jenisMobil,
            onJenisChange = {jenisMobil = it},
            warnaMobil = warnaMobil,
            onWarnaChange = {warnaMobil = it},
            tahunKeluaran = tahuKeluaran,
            onTahunChange = {tahuKeluaran = it},
            status = status,
            onStatusChange = {status = it},
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun DeleteData(delete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = {expanded = true}) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.opsi_lainnya),
            tint = Color.White
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false}
        ) {
            DropdownMenuItem(
                text = {
                       Text(text = stringResource(R.string.hapus_mobil))
                },
                onClick = {
                    expanded = false
                delete()
                }
            )
        }

    }
}
@Composable
fun FormGarage(
    merkMobil: String, onMerkChange: (String) -> Unit,
    jenisMobil: String, onJenisChange: (String) -> Unit,
    warnaMobil: String, onWarnaChange: (String) -> Unit,
    tahunKeluaran: String, onTahunChange: (String) -> Unit,
    status: String, onStatusChange: (String) -> Unit,

    modifier: Modifier
){
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        OutlinedTextField(
            value = merkMobil,
            onValueChange = {onMerkChange(it)},
            label = {Text(text = stringResource(R.string.merk_mobil))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
            )

        OutlinedTextField(
            value = jenisMobil,
            onValueChange = {onJenisChange(it)},
            label = {Text(text = stringResource(R.string.jenis_mobil))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = warnaMobil,
            onValueChange = {onWarnaChange(it)},
            label = {Text(text = stringResource(R.string.warna_mobil))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = tahunKeluaran,
            onValueChange = {onTahunChange(it)},
            label = {Text(text = stringResource(R.string.tahun))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .padding(top = 6.dp)
                .fillMaxWidth()
                .border(1.dp, Color.DarkGray, RoundedCornerShape(4.dp))
        ) {
            radioOptions.forEach { text ->
                StatusOption(
                    label = text,
                    isSelected = status == text,
                    modifier = Modifier
                        .selectable(
                            selected = status == text,
                            onClick = { onStatusChange(text) },
                            role = Role.RadioButton
                        )
                        .weight(2f)
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun StatusOption(label: String, isSelected: Boolean, modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(
            selected = isSelected,
            onClick = null
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )

    }

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
   EfwanGarageTheme {
        DetailScreen(rememberNavController())
    }
}