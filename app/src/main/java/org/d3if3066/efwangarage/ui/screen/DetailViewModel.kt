package org.d3if3066.efwangarage.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3066.efwangarage.database.GarageDao
import org.d3if3066.efwangarage.model.Garage

class DetailViewModel(private val dao: GarageDao): ViewModel() {

    fun insert(merkMobil: String, jenisMobil: String, tahunKeluaran: String, warnaMobil: String, status: String) {
        val garage = Garage(
            merkMobil = merkMobil,
            jenisMobil = jenisMobil,
            tahunKeluaran = tahunKeluaran,
            warnaMobil = warnaMobil,
            status = status
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(garage)
        }
    }
}