package com.pg.salud

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.pg.salud.api.APIServices
import com.pg.salud.databinding.ActivityMainBinding
import com.pg.salud.databinding.FragmentRegistroImcBinding
import com.pg.salud.ui.IMC.IMC
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("xDDDDD")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_IMC, R.id.navigation_notifications,R.id.navigation_profile
            )
        )
        supportActionBar?.hide()
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{ controller, destination, arguments ->
//            if(destination.id == R.id.wordDetailsFragment) {
//                actionBar?.hide()
//            } else {
//                actionBar?.show()
//            }
            if(destination.label == "IMC") {
//                getMethod()
                println("IMC :D")
            }
        }

    }
    fun getMethod() {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://dummy.restapiexample.com")
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
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )
                    Log.d("Pretty Printed JSON :", prettyJson)

//                    val intent = Intent(this@MainActivity, IMC::class.java)
//                    intent.putExtra("json_results", prettyJson)
//                    FragmentRegistroImcBinding.inflate(layoutInflater, )
                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }
}