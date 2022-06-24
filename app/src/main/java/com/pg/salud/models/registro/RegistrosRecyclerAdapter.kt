package com.pg.salud.models.registro.task

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.pg.salud.databinding.RegistroCardBinding
import com.pg.salud.R
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class RegistrosRecyclerAdapter(
    private var registroData: List<Registro> = ArrayList(),
    private val onItemClick: (Registro) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding: RegistroCardBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = RegistroCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

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

    fun updateData(registroData: List<Registro>) {
        this.registroData = registroData
    }

    inner class RegistroViewHolder(binding: RegistroCardBinding, clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }

        fun bind(registro: Registro) {
            println("El bind sera: ${registro}")
            with(binding) {
                println("El bind en with sera: ${registro}")
                println(registro)
                var getDate: Long = registro.createdAt.seconds * 1000
                val fecha = Date(getDate)
                val dia = fecha.day
                val mes = fecha.month
                val year = fecha.year
                val set_imc = String.format("%.2f", registro.imc)

                dateRegistro.text = "${dia}/${mes}/${year}"
                imc.text = "IMC ${set_imc}kg/m²"
                weight.text = "${registro.weight.toString()}kg"
                if(registro.diff > 0) {
                    diff.text = "+${registro.diff.toString()}kg"
                    diff.setTextColor(Color.parseColor("#F91111"))
                } else {
                    diff.text = "-${registro.diff.toString()}kg"
                    diff.setTextColor(Color.parseColor("#0FCF9F"))
                }
                val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
            }
        }
    }
}