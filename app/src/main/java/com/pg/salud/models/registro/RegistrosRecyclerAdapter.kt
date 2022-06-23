package com.pg.salud.models.registro.task

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.pg.salud.databinding.RegistroCardBinding
import com.pg.salud.R

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

            with(binding) {
                labelDescription.text = registro.signature
                labelAuthor.text = registro.name

                val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
            }
        }
    }
}