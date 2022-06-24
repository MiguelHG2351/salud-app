package com.pg.salud.models.Home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TareasViewModel: ViewModel() {
    private val selectedNews : MutableLiveData<Tarea?> = MutableLiveData()
    private val newsData: MutableLiveData<List<Tarea>> = MutableLiveData()


    var selected: Tarea?
        get() = selectedNews.value
        set(value)  { selectedNews.value = value }

    val data: MutableLiveData<List<Tarea>>
        get() {
            if (newsData.value.isNullOrEmpty()) {
                val table = ArrayList<Tarea>()
                val lista = table
                newsData.value =  lista
            }

            return  newsData
        }
    fun updateViewData(data: List<Tarea>) {
        newsData.postValue(data)
    }
}