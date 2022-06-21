package com.pg.salud.ui.recycle_list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pg.salud.ui.recycle_list.retro.APIServices
import com.pg.salud.ui.recycle_list.retro.RetroInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivityViewModel: ViewModel() {
    lateinit var recyclerListLiveData : MutableLiveData<UserList>

    init {
        recyclerListLiveData = MutableLiveData()
    }

    fun getRecyclerListObserver(): MutableLiveData<UserList> {
        return recyclerListLiveData
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(APIServices::class.java)
            val response  = retroInstance.getUsers()
            println(response.items)
            Log.i("xxxxxxxxx", response.items[0].name)
            recyclerListLiveData.postValue(response)
        }
    }
}