package com.pg.salud.models.registro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pg.salud.mocks.FakeRegistroDataSource
import com.pg.salud.models.registro.task.Registro

class RegistroViewModel: ViewModel() {
    private val selectedNews : MutableLiveData<Registro?> = MutableLiveData()
    private val newsData: MutableLiveData<List<Registro>> = MutableLiveData()

    var selected: Registro?
        get() = selectedNews.value
        set(value)  { selectedNews.value = value }

    val data: MutableLiveData<List<Registro>>
        get() {
            if (newsData.value.isNullOrEmpty()) {
                newsData.value = FakeRegistroDataSource.createDataSet()
            }
            return  newsData
        }
}