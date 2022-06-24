package com.pg.salud.ui.calc

import android.icu.number.IntegerWidth
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pg.salud.R
import com.pg.salud.api.APIServices
import com.pg.salud.databinding.CalculadoraImcBinding
import com.pg.salud.models.registro.task.RegistroForm
import com.pg.salud.models.registro.task.Table
import com.pg.salud.retro.RetroInstance
import kotlinx.coroutines.*

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
            val peso = binding.weightAdd.text.toString().toDouble()
            val altura = binding.heightAdd.text.toString().toDouble()
            val diff = binding.diffAdd.text.toString().toDouble()
            val imcResult = peso / (altura*altura)

            if(imcResult > 18.8 && imcResult < 24.9) {
                binding.stateRegister.text = "Normal"
            } else if(imcResult > 24.9 && imcResult < 29.9) {
                binding.stateRegister.text = "Sobrepeso"
            } else if(imcResult > 29.9 && imcResult < 34.9) {
                binding.stateRegister.text = "Obesida grado 2"
            }
            binding.imcResult.text = imcResult.toString()
            CoroutineScope(Dispatchers.IO).launch {
                val retroInstance = RetroInstance.getRetroInstance().create(APIServices::class.java)
                val table = RegistroForm(arguments?.get("email").toString(), diff, altura, peso)
                val response  = retroInstance.createRegistro(table)

                println(response)
                withContext(Dispatchers.Main) {
                    val navController = findNavController()
                    navController.navigate(R.id.navigation_IMC)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}