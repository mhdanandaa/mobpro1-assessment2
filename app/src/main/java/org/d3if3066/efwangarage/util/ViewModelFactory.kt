package org.d3if3066.efwangarage.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3066.efwangarage.database.GarageDao
import org.d3if3066.efwangarage.ui.screen.DetailViewModel
import org.d3if3066.efwangarage.ui.screen.MainViewModel

class ViewModelFactory(
    private val dao: GarageDao
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(dao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}