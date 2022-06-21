package com.pg.salud.ui.IMC

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pg.salud.adapters.IMCAdapter
import com.pg.salud.databinding.FragmentRegistroImcBinding
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.WithFragmentBindings
import dagger.hilt.android.scopes.FragmentScoped

//@WithFragmentBindings
@FragmentScoped
class IMC : Fragment() {
    private var _binding: FragmentRegistroImcBinding? = null
    private val binding get() = _binding!!
    private val viewModel: IMCViewModel by viewModels()

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

        val imcAdapter = IMCAdapter()
        val context = this.context

        binding.apply {
            cardRegistro.apply {
                adapter = imcAdapter
                layoutManager = LinearLayoutManager(this.context)
            }

            viewModel.IMCs.observe(viewLifecycleOwner) { users ->
                imcAdapter.submitList(users)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
