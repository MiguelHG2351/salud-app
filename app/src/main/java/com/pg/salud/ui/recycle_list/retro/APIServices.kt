package com.pg.salud.ui.recycle_list.retro

import com.pg.salud.ui.IMC.IMC
import com.pg.salud.ui.recycle_list.RecyclerList
import com.pg.salud.ui.recycle_list.User
import com.pg.salud.ui.recycle_list.UserList
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
    suspend fun getUsers(): UserList
//    https://api-salud.herokuapp.com
}