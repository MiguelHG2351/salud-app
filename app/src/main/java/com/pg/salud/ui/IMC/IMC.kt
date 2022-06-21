package com.pg.salud.ui.IMC

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pg.salud.databinding.FragmentRegistroImcBinding
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.gson.*

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
        getData()
        println("xD2")
    }


    fun getData() {
        val client = HttpClient(CIO) {
                install(ContentNegotiation) {
                    gson()
                }
        }
        CoroutineScope(Dispatchers.IO).launch {
            val joke: List<Users> = client.get("https://api-salud.herokuapp.com/users").body()
            withContext(Dispatchers.Main) {
                println(joke.get(0).username)
                println(joke.get(1).username)
                println(joke.get(2).username)
            }
        }
        // println(joke.)
    }

//        super.onDestroyView()
//        val init_api = Retrofit.Builder().baseUrl("https://api-salud.herokuapp.com").build()
//        val data = init_api.create(APIServices::class.java)
//        data.getUsers(this@IMC, IMC)
//    override fun onDestroyView() {
//
//    }

}

data class Users(val username: String, val email: String, val name: String)
