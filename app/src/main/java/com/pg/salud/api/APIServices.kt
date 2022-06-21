package com.pg.salud.api

import com.pg.salud.ui.IMC.IMC
import retrofit2.http.GET

//import okhttp3.RequestBody
//import okhttp3.ResponseBody
//import retrofit2.Response
//import retrofit2.http.*
interface APIServices {

    companion object {
        const val BASE_URL = "https://api-salud.herokuapp.com/"
    }

//    Request using @Query (e.g https://reqres.in/api/users?page=2)
    @GET("/users")
    suspend fun getUsers(): List<com.pg.salud.data.IMC>
//    https://api-salud.herokuapp.com
}