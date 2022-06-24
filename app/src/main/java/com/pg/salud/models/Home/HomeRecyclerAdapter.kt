package com.pg.salud.models.Home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.pg.salud.R
import com.pg.salud.databinding.RecordatoriosCardBinding
import com.pg.salud.databinding.RegistroCardBinding
import com.pg.salud.databinding.TareaCardBinding
import com.pg.salud.models.registro.task.Registro
import java.util.*
import kotlin.collections.ArrayList

class TareasRecyclerAdapter(
    private var registroData: List<Tarea> = ArrayList(),
    private val onItemClick: (Tarea) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding: TareaCardBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = TareaCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RegistroViewHolder(binding) { onItemClick(registroData[it]) }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RegistroViewHolder -> {
                holder.bind(registroData[position])
            }
        }
    }

    override fun getItemCount(): Int {
//        println("La longitud es ${registroData}")
        return registroData.size
    }

    fun updateData(registroData: List<Tarea>) {
        this.registroData = registroData
    }

    inner class RegistroViewHolder(binding: TareaCardBinding, clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }

        fun bind(registro: Tarea) {
            println("El bind sera: ${registro}")
            with(binding) {
                println("El bind en with sera: ${registro}")
                println(registro)
                tiempo.text = "${registro.time} minutos"
                tagTarea.text = registro.tag
                title.text = registro.title
                description.text = registro.description


                val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
            }
        }
    }
}

class RecordatoriosRecyclerAdapter(
    private var registroData: List<Recordatorio> = ArrayList(),
    private val onItemClick: (Recordatorio) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding: RecordatoriosCardBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = RecordatoriosCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RegistroViewHolder(binding) { onItemClick(registroData[it]) }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RegistroViewHolder -> {
                holder.bind(registroData[position])
            }
        }
    }

    override fun getItemCount(): Int {
//        println("La longitud es ${registroData}")
        return registroData.size
    }

    fun updateData(registroData: List<Recordatorio>) {
        this.registroData = registroData
    }

    inner class RegistroViewHolder(binding: RecordatoriosCardBinding, clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }

        fun bind(registro: Recordatorio) {
            println("El bind sera: ${registro}")
            with(binding) {
                println("El bind en with sera: ${registro}")
                println(registro)
                tiempo.text = "${registro.time} minutos"
                tagTarea.text = registro.tag
                title.text = registro.title
                description.text = registro.description


                val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
            }
        }
    }
}


//class RegistrosRecyclerAdapter(
//    private var registroData: List<Registro> = ArrayList(),
//    private val onItemClick: (Registro) -> Unit
//): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//}