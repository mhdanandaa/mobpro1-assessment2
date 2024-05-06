package org.d3if3066.efwangarage.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if3066.efwangarage.database.GarageDao
import org.d3if3066.efwangarage.model.Garage

class MainViewModel(dao: GarageDao): ViewModel() {

    val data: StateFlow<List<Garage>> = dao.getCar().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}