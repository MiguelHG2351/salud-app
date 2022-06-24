package com.pg.salud.ui.tareas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.pg.salud.api.APIServices
import com.pg.salud.databinding.FragmentNotificationsBinding
import com.pg.salud.models.recordatorio.RecordatorioRequest
import com.pg.salud.models.tasks.TaskRequest
import com.pg.salud.retro.RetroInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentTareas: Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private val correoActual = FirebaseAuth.getInstance().currentUser?.email

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.agregar.setOnClickListener {
            val tag = binding.tareasTag.text.toString()
            val title = binding.tareasName.text.toString()
            val description = binding.tareasDescription.text.toString()
            val time = binding.tareasTiempo.text.toString().toInt()


            CoroutineScope(Dispatchers.IO).launch {
                if(correoActual?.length!! > 0) {
                    val retroInstance = RetroInstance.getRetroInstance().create(APIServices::class.java)
                    val response  = retroInstance.getUsers(correoActual)
                    val tareasData = TaskRequest(response.id, tag, title, description, time)
                    val submitRecordatorio = retroInstance.createTareas(tareasData)

                    withContext(Dispatchers.Main) {
                        binding.tareasTag.text.clear()
                        binding.tareasName.text.clear()
                        binding.tareasDescription.text.clear()
                        binding.tareasTiempo.text.clear()
                        Toast.makeText(context, "Enviado correctamente", Toast.LENGTH_LONG).show()
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