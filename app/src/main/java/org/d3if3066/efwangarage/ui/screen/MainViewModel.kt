package org.d3if3066.efwangarage.ui.screen



import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.d3if3066.efwangarage.model.Design
import org.d3if3066.efwangarage.network.ApiStatus
import org.d3if3066.efwangarage.network.DesignApi
import java.lang.Exception

class MainViewModel: ViewModel() {

    var data = mutableStateOf(emptyList<Design>())
        private set

    var status = MutableStateFlow(ApiStatus.LOADING)
        private set
    init {
        retrieveData()
    }

    fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.value = ApiStatus.LOADING
            try {
                data.value = DesignApi.service.getDesign()
                status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value =  ApiStatus.FAILED
            }
        }
    }
}