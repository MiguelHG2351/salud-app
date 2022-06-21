package com.pg.salud.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pg.salud.data.IMC
import com.pg.salud.databinding.RegistroImcItemBinding

class IMCAdapter: RecyclerView.Adapter<IMCAdapter.IMCViewHolder>() {
    private lateinit var lista: List<IMC>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IMCViewHolder {
        val binding = RegistroImcItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IMCViewHolder(binding)
    }

    fun submitList(list: List<IMC?>?) {
        this.lista = list as List<IMC>
    }

    override fun onBindViewHolder(holder: IMCViewHolder, position: Int) {
        val currentItem = lista.get(position)
        return holder.bind(currentItem)
    }
    class IMCViewHolder(private val binding: RegistroImcItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(imc: IMC) {
            binding.apply {
                imcText.text = "IMC ${imc.name}kg/m²"
                peso.text = imc.username.toString()
                diferencia.text = imc.signature.toString()
            }
        }
    }


    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}