package com.pg.salud.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pg.salud.data.IMC
import com.pg.salud.databinding.FragmentRegistroImcBinding

class IMCAdapter: ListAdapter<IMC, IMCAdapter.IMCViewHolder>(IMCComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IMCViewHolder {
        val binding = FragmentRegistroImcBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IMCViewHolder(binding)
    }

//    override fun onBindViewHolder(holder: IMCViewHolder, position: Int) {
//        val currentItem = getItem(position)
//        if(currentItem != null) {
//            holder.bind(currentItem)
//        }
//    }
    class IMCViewHolder(private val binding: FragmentRegistroImcBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(imc: IMC) {
            binding.apply {

            }
        }
    }
    class IMCComparator: DiffUtil.ItemCallback<IMC>() {
        override fun areItemsTheSame(oldItem: IMC, newItem: IMC) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: IMC, newItem: IMC) = oldItem == newItem
    }
}