package com.pg.salud.ui.IMC

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.pg.salud.databinding.FragmentRegistroImcBinding
import com.pg.salud.databinding.RowsBinding
import com.pg.salud.ui.recycle_list.RecyclerListFragment
import com.pg.salud.ui.recycle_list.RecyclerViewAdapter


class IMC : Fragment() {
    private var _binding: FragmentRegistroImcBinding? = null
    private val binding get() = _binding!!
    private val recyclerView: RecyclerView? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistroImcBinding.inflate(inflater, container, false)
        val listBin = RowsBinding.inflate(layoutInflater)
        val listRoot = listBin.root
        binding.fragmentRegistro.addView(listRoot)
        println("nooooooooooooooo")
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
//    private fun setupFragment() {
//
//        val fragment  = RecyclerListFragment.newInstance()
//        val fragmentManager: FragmentManager = supportFragmentManager
//        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(android.R.id.content, fragment)
//        fragmentTransaction.commit()
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
