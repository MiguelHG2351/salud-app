package com.pg.salud.ui.recycle_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pg.salud.R
import com.pg.salud.ui.recycle_list.RecyclerList
import com.pg.salud.ui.recycle_list.RecyclerViewAdapter
import com.pg.salud.ui.recycle_list.MainActivityViewModel

class RecyclerListFragment: Fragment() {

    private lateinit var recyclerAdapter : RecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.recycle_view_list, container, false)

        initViewModel(view)
        initViewModel()
        println("kkjdfslsdjkldj")
        return view

    }

    private fun initViewModel(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decortion  = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decortion)

        recyclerAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerAdapter


    }

    private fun initViewModel() {
        val viewModel  = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<UserList> {
            if(it != null) {
                recyclerAdapter.setUpdatedData(it.items)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            RecyclerListFragment()
    }
}