package com.torres.myapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.torres.myapplication.R
import com.torres.myapplication.databinding.FragmentModificarBinding
import com.torres.myapplication.ui.viewmodel.ModificarFragmentVM


class ModificarFragment : Fragment() {

    private lateinit var binding: FragmentModificarBinding
    private val args: ModificarFragmentArgs by navArgs()
    private val modificarFragmentVM: ModificarFragmentVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModificarBinding.bind(inflater.inflate(R.layout.fragment_modificar, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtHola.text = args.name
        initListeners()
        initObservers()
    }

    private fun initObservers(){
        modificarFragmentVM.cLive.observe(viewLifecycleOwner){
            binding.txtHola.text = it.toString()
        }
    }


    private fun initListeners(){
        binding.btnContar.setOnClickListener {
            modificarFragmentVM.funListeners()
        }
    }

}