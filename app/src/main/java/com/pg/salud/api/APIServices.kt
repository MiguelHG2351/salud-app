package com.pg.salud.api

import com.pg.salud.models.recordatorio.RecordatorioRequest
import com.pg.salud.models.registro.task.RegistroForm
import com.pg.salud.models.registro.task.RegistroList
import com.pg.salud.models.Home.TableHome
import com.pg.salud.models.registro.task.Table
import com.pg.salud.models.tasks.TaskRequest
import retrofit2.http.*

interface APIServices {
    companion object {
        const val BASE_URL = "https://api-salud.herokuapp.com/"
    }

    //    Request using @Query (e.g https://reqres.in/api/users?page=2)
    @GET("/table/{email}")
    suspend fun getUsers(@Path("email") username: String): RegistroList

    @GET("/table/{email}")
    suspend fun getTables(@Path("email") username: String): TableHome
//
    @Headers("Content-Type: application/json")
    @POST("/table")
    suspend fun createTable(@Body user: Table)


    @Headers("Content-Type: application/json")
    @POST("/registro")
    suspend fun createRegistro(@Body registro: RegistroForm)

    @Headers("Content-Type: application/json")
    @POST("/reminder")
    suspend fun createRecordatorio(@Body registro: RecordatorioRequest)

    @Headers("Content-Type: application/json")
    @POST("/task")
    suspend fun createTareas(@Body registro: TaskRequest)

    
//    https://api-salud.herokuapp.com
}