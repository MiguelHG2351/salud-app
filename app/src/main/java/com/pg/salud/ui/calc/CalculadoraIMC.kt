package com.pg.salud.ui.calc

import android.icu.number.IntegerWidth
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.util.PatternsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pg.salud.R
import com.pg.salud.api.APIServices
import com.pg.salud.databinding.CalculadoraImcBinding
import com.pg.salud.models.registro.task.RegistroForm
import com.pg.salud.models.registro.task.Table
import com.pg.salud.retro.RetroInstance
import kotlinx.coroutines.*
import java.util.regex.Pattern
import android.content.Context
import com.pg.salud.MainActivity
import java.security.AccessController.getContext

class CalculadoraIMC: Fragment() {
    private var _binding: CalculadoraImcBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CalculadoraImcBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println(arguments?.get("email").toString())
        binding.backToRegistro.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.navigation_IMC)
        }

        binding.saveRegistro.setOnClickListener {
            val peso = binding.weightAdd.text.toString()
            val altura = binding.heightAdd.text.toString()
            val diff = binding.diffAdd.text.toString()


            if(peso.isNotEmpty() && altura.isNotEmpty() && diff.isNotEmpty()){
                //una vez pasado el test de verificacion de campos vacios

                val imcResult = peso.toDouble() / (altura.toDouble() * altura.toDouble())

                if (imcResult > 18.8 && imcResult < 24.9) {
                    binding.stateRegister.text = "Normal"
                } else if (imcResult > 24.9 && imcResult < 29.9) {
                    binding.stateRegister.text = "Sobrepeso"
                } else if (imcResult > 29.9 && imcResult < 34.9) {
                    binding.stateRegister.text = "Obesida grado 2"
                }
                binding.imcResult.text = imcResult.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    val retroInstance =
                        RetroInstance.getRetroInstance().create(APIServices::class.java)
                    val table = RegistroForm(arguments?.get("email").toString(), diff.toDouble(), altura.toDouble(), peso.toDouble())
                    val response = retroInstance.createRegistro(table)

                    println(response)
                    withContext(Dispatchers.Main) {
                        val navController = findNavController()
                        navController.navigate(R.id.navigation_IMC)
                    }
                }
            }
            else{
                Toast.makeText(activity, "No deje campos vacios", Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}