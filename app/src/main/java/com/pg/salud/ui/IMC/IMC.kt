package com.pg.salud.ui.IMC

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pg.salud.api.APIServices
import com.pg.salud.databinding.FragmentRegistroImcBinding
import com.pg.salud.models.registro.RegistroViewModel
import com.pg.salud.models.registro.task.Registro
import com.pg.salud.models.registro.task.RegistrosRecyclerAdapter
import com.pg.salud.retro.RetroInstance
import com.pg.salud.ui.decorations.PaddingItemDecoration
import kotlinx.coroutines.*


class IMC : Fragment() {
    private var _binding: FragmentRegistroImcBinding? = null
    private val binding get() = _binding!!
    private val newsModel: RegistroViewModel by activityViewModels()

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

        newsModel.data.observe(viewLifecycleOwner) { news ->
            newsAdapter.updateData(news)
        }

        CoroutineScope(Dispatchers.IO).launch {
            val retroInstance = RetroInstance.getRetroInstance().create(APIServices::class.java)
            val response  = retroInstance.getUsers()
            withContext(Dispatchers.Main) {
                println(response.items)
                if(response.items.size > 0) {
                    Log.i("xxxxxxxxx", response.items[0].imc.toString())
                    newsModel.updateViewData(response.items)
                    newsAdapter.notifyDataSetChanged()
                }
            }
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
