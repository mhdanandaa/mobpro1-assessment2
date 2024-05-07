package org.d3if3066.efwangarage.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3066.efwangarage.database.GarageDao
import org.d3if3066.efwangarage.model.Garage

class DetailViewModel(private val dao: GarageDao): ViewModel() {

    fun insert(merkMobil: String, jenisMobil: String, warnaMobil: String, tahunKeluaran: String, status: String) {
        val garage = Garage(
            merkMobil = merkMobil,
            jenisMobil = jenisMobil,
            warnaMobil = warnaMobil,
            tahunKeluaran = tahunKeluaran,
            status = status
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(garage)
        }
    }

    suspend fun getCar(id: Long): Garage? {
        return dao.getCarById(id)
    }

    fun update(id: Long, merkMobil: String, jenisMobil: String, warnaMobil: String, tahunKeluaran: String, status: String) {
        val garage = Garage(
            id = id,
            merkMobil = merkMobil,
            jenisMobil = jenisMobil,
            warnaMobil = warnaMobil,
            tahunKeluaran = tahunKeluaran,
            status = status
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(garage)
        }

    }



}