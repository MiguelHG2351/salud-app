package com.pg.salud.models.registro

import android.util.Log
import androidx.lifecycle.*
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.pg.salud.mocks.FakeRegistroDataSource
import com.pg.salud.models.registro.task.Registro
import com.pg.salud.models.registro.task.RegistroList
import com.pg.salud.retro.RetroInstance
import com.pg.salud.ui.IMC.IMC
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.http.GET

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

            viewModelScope.launch(Dispatchers.IO) {
                val retroInstance = RetroInstance.getRetroInstance().create(APIServices::class.java)
                val response  = retroInstance.getUsers()
                println(response.items)
                Log.i("xxxxxxxxx", response.items[0].name)
                newsData.postValue(response.items)
            }

            return  newsData
        }
    fun updateViewData(data: List<Registro>) {
            newsData.postValue(data)
    }
}

interface APIServices {

    companion object {
        const val BASE_URL = "https://api-salud.herokuapp.com/"
    }

    //    Request using @Query (e.g https://reqres.in/api/users?page=2)
    @GET("/users")
    suspend fun getUsers(): RegistroList
//    https://api-salud.herokuapp.com
}