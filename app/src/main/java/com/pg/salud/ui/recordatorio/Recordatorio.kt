package com.pg.salud.ui.recordatorio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.pg.salud.api.APIServices
import com.pg.salud.databinding.FragmentRecordatoriosInBinding
import com.pg.salud.models.recordatorio.RecordatorioRequest
import com.pg.salud.retro.RetroInstance
import com.pg.salud.ui.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Recordatorio: Fragment() {
    private var _binding: FragmentRecordatoriosInBinding? = null
    private val binding get() = _binding!!
    private val correoActual = FirebaseAuth.getInstance().currentUser?.email

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentRecordatoriosInBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /* val textView: TextView = binding.textHome
         homeViewModel.text.observe(viewLifecycleOwner) {
             textView.text = it
         }*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var fecha = ""
        val calendario = binding.calendarView
        Toast.makeText(context, "Selected date:", Toast.LENGTH_LONG).show()
        calendario.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val hora = binding.horaRecorda.text
            println("xDDDDD")
            Toast.makeText(context, "Selected date: $dayOfMonth/$month/$year", Toast.LENGTH_LONG).show()
            fecha = "${month}/${dayOfMonth}/${year} ${hora}"
        }
        binding.sendRecordatorio.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                if(correoActual?.length!! > 0) {
                    val retroInstance = RetroInstance.getRetroInstance().create(APIServices::class.java)
                    val response  = retroInstance.getUsers(correoActual)
                    val recordatorioData = RecordatorioRequest(response.id, binding.tag.text.toString(),
                        binding.titleRecordatorio.text.toString(), binding.descriptionRecorda.text.toString(),
                        fecha)
                    val submitRecordatorio = retroInstance.createRecordatorio(recordatorioData)

                    withContext(Dispatchers.Main) {
                        binding.tag.text.clear()
                        binding.titleRecordatorio.text.clear()
                        binding.descriptionRecorda.text.clear()
                        binding.horaRecorda.text.clear()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}