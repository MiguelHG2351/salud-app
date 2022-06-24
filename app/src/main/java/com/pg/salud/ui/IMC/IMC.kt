package com.pg.salud.ui.IMC

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pg.salud.api.APIServices
import com.pg.salud.databinding.FragmentRegistroImcBinding
import com.pg.salud.models.registro.RegistroViewModel
import com.pg.salud.models.registro.task.Registro
import com.pg.salud.models.registro.task.RegistrosRecyclerAdapter
import com.pg.salud.retro.RetroInstance
import com.pg.salud.ui.decorations.PaddingItemDecoration
import kotlinx.coroutines.*
import com.google.firebase.auth.FirebaseAuth
import com.pg.salud.R


class IMC : Fragment() {
    private var _binding: FragmentRegistroImcBinding? = null
    private val binding get() = _binding!!
    private val newsModel: RegistroViewModel by activityViewModels()
    private val correoActual = FirebaseAuth.getInstance().currentUser?.email

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistroImcBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        var emailId = ""
        val newsAdapter = RegistrosRecyclerAdapter {
            showAuthor(it) // On Item Click
        }

        binding.registroRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireActivity().application
            )
            adapter = newsAdapter
            addItemDecoration(PaddingItemDecoration(30, 0, 30, 100))
        }

//        newsModel.data.observe(viewLifecycleOwner) { news ->
//            newsAdapter.updateData(news)
//        }

        CoroutineScope(Dispatchers.IO).launch {
            if(correoActual?.length!! > 0) {
                val fake = ArrayList<Registro>()
                val retroInstance = RetroInstance.getRetroInstance().create(APIServices::class.java)
                val response  = retroInstance.getUsers(correoActual)
                withContext(Dispatchers.Main) {
                    println("La data sera")
                    println(response)
                    println(response.registro)
                    Log.i("xxxxxxxxx", response.current.toString())
                    newsModel.updateViewData(response.registro)
                    newsAdapter.updateData(response.registro)
                    newsAdapter.notifyDataSetChanged()
                    emailId = response.id
                    binding.CurrentWeight.text = "${response.current}kg"
                    binding.CurrentWeightNumber.text = "${response.objective}kg"
                    binding.needWeightWeightNumber.text = "${response.remaining}kg"
                }
            }
        }
        binding.goToRegisterForm.setOnClickListener {
            val bundle = bundleOf()
            bundle.putString("email", emailId)
            val navController = findNavController()
            navController.navigate(R.id.navigation_calc, bundle)
        }
    }

    private fun showAuthor(registro: Registro) {
        newsModel.selected = registro
        Toast.makeText(context,
            newsModel.selected?.imc.toString() ?: "The author is null",
            Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
