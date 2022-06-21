package com.pg.salud.ui.IMC

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.pg.salud.api.APIServices
import com.pg.salud.databinding.FragmentRegistroImcBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit


class IMC : Fragment() {
    private lateinit var binding: FragmentRegistroImcBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistroImcBinding.inflate(inflater, container, false)

//        val results = intent.getStringExtra("json_results")
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("xD1")
        getMethod()
        println("xD2")
    }
    fun getMethod() {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-salud.herokuapp.com")
            .build()

        // Create Service
        val service = retrofit.create(APIServices::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            /*
             * For @Query: You need to replace the following line with val response = service.getEmployees(2)
             * For @Path: You need to replace the following line with val response = service.getEmployee(53)
             */

            // Do the GET request and get response
            val response = service.getUsers()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val dataJson = response.body()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )
                    Log.d("Pretty Printed JSON :", prettyJson)
//                    Log.d("Pretty Printed JSON 2 :", dataJson)

//                    val intent = Intent(this@MainActivity, IMC::class.java)
//                    intent.putExtra("json_results", prettyJson)
//                    FragmentRegistroImcBinding.inflate(layoutInflater, )
                    binding.showUsers.text = prettyJson
                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }
//        super.onDestroyView()
//        val init_api = Retrofit.Builder().baseUrl("https://api-salud.herokuapp.com").build()
//        val data = init_api.create(APIServices::class.java)
//        data.getUsers(this@IMC, IMC)
//    override fun onDestroyView() {
//
//    }

}