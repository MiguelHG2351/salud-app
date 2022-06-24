package com.pg.salud.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.pg.salud.api.APIServices
import com.pg.salud.databinding.FragmentHomeBinding
import com.pg.salud.models.Home.*
import com.pg.salud.models.registro.task.Registro
import com.pg.salud.models.registro.task.RegistrosRecyclerAdapter
import com.pg.salud.retro.RetroInstance
import com.pg.salud.ui.decorations.PaddingItemDecoration
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@FragmentScoped
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val correoActual = FirebaseAuth.getInstance().currentUser?.email
    private val newsModel: TareasViewModel by activityViewModels()
    private val newsModel2: RecordatorioViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

       /* val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.username.text=correoActual.toString()
        try {
            initRecyclerView()
        } catch (e: Throwable) {
            println(e)
        }
    }


    private fun initRecyclerView() {
        var emailId = ""
        val newsAdapter = TareasRecyclerAdapter {
            showAuthor(it) // On Item Click
        }
        val news2Adapter = RecordatoriosRecyclerAdapter {
            showAuthor2(it) // On Item Click
        }

        binding.tareasRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireActivity().application
            )
            adapter = newsAdapter
        }

        binding.recordatorioRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireActivity().application
            )
            adapter = news2Adapter
        }

//        newsModel.data.observe(viewLifecycleOwner) { news ->
//            newsAdapter.updateData(news)
//        }

        CoroutineScope(Dispatchers.IO).launch {
            if(correoActual?.length!! > 0) {
                val fake = ArrayList<Registro>()
                val retroInstance = RetroInstance.getRetroInstance().create(APIServices::class.java)
                val response  = retroInstance.getTables(correoActual)
                withContext(Dispatchers.Main) {
                    println("La data sera")
                    println(response)
                    println(response.registro)
                    Log.i("xxxxxxxxx", response.current.toString())
                    if(response.tasks != null) {
                        newsModel.updateViewData(response.tasks)
                        newsAdapter.updateData(response.tasks)
                        newsAdapter.notifyDataSetChanged()
                    }
                    if(response.reminder != null) {
                        newsModel2.updateViewData(response.reminder)
                        news2Adapter.updateData(response.reminder)
                        news2Adapter.notifyDataSetChanged()
                    }
                    emailId = response.id
                }
            }
        }
    }

    private fun showAuthor(registro: Tarea) {
        newsModel.selected = registro
        Toast.makeText(context,
            newsModel.selected?.description.toString() ?: "The author is null",
            Toast.LENGTH_LONG).show()
    }

    private fun showAuthor2(registro: Recordatorio) {
        newsModel2.selected = registro
        Toast.makeText(context,
            newsModel.selected?.description.toString() ?: "The author is null",
            Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}