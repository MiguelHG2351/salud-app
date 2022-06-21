package com.pg.salud.ui.IMC

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pg.salud.api.APIServices
import com.pg.salud.data.IMC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IMCViewModel @Inject constructor(
    api: APIServices
) : ViewModel() {
    private val IMCsLiveData = MutableLiveData<List<IMC>>()
    val IMCs: LiveData<List<IMC>> = IMCsLiveData

    init {
        viewModelScope.launch {
            val users = api.getUsers()
            delay(2000)
            IMCsLiveData.value = users
        }
    }
}