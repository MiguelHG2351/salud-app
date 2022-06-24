package com.pg.salud.api

import com.pg.salud.models.Register.User
import com.pg.salud.models.registro.task.RegistroList
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIServices {
    companion object {
        const val BASE_URL = "https://api-salud.herokuapp.com/"
    }

    //    Request using @Query (e.g https://reqres.in/api/users?page=2)
    @GET("/table")
    suspend fun getUsers(): RegistroList
//
//    @Headers("Content-Type: application/json")
//    @POST("/table")
//    suspend fun createTable(@Body user: User)
//    https://api-salud.herokuapp.com
}