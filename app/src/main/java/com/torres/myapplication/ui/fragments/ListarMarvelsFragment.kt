package com.torres.myapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.torres.myapplication.R
import com.torres.myapplication.databinding.FragmentListarMarvelsBinding
import com.torres.myapplication.logic.usercases.marvel.GetMarvelCharsUserCase
import com.torres.myapplication.ui.adapters.ListMarvelCharsAdapterDiffUtil
import com.torres.myapplication.ui.viewmodel.main.ListMarvelCharsVM
import kotlinx.coroutines.launch

class ListarMarvelsFragment : Fragment() {

    private lateinit var binding: FragmentListarMarvelsBinding
    private lateinit var adapter: ListMarvelCharsAdapterDiffUtil
    private val items: ListMarvelCharsVM by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentListarMarvelsBinding.bind(inflater.inflate(R.layout.fragment_listar_marvels, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariables()
        initListeners()
        initObservers()
    }

    private fun initVariables(){

        adapter = ListMarvelCharsAdapterDiffUtil()
        binding.rvListMarvel.adapter = adapter
        binding.rvListMarvel.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
    }
    private fun initListeners(){

    }
    private fun initObservers(){

        items.itemMarvel.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    private fun initData(){
        items.initData()
    }

}