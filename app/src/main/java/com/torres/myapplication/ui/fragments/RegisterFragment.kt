package com.torres.myapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.torres.myapplication.R
import com.torres.myapplication.data.local.database.entities.UsersDB
import com.torres.myapplication.data.local.repository.DataBaseRepository
import com.torres.myapplication.databinding.FragmentRegisterBinding
import com.torres.myapplication.logic.usercases.login.CreateUserWithNameAndPassword
import com.torres.myapplication.ui.core.ManageUIState
import com.torres.myapplication.ui.core.MyApplication
import com.torres.myapplication.ui.core.UIStates
import com.torres.myapplication.ui.viewmodel.login.RegistrarFragmentVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private lateinit var con: DataBaseRepository

    private val registerFragmentVM: RegistrarFragmentVM by viewModels()

    private lateinit var manageUISate: ManageUIState

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.bind(
            inflater.inflate(R.layout.fragment_register, container, false)
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        con = MyApplication.getDBConnection()

        initVariables()
        initListeners()
        initObservables()
    }

    private fun initListeners(){
        binding.btnBack.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }

        binding.btnSave.setOnClickListener {

            MaterialAlertDialogBuilder(requireActivity())
                .setTitle("Informacion")
                .setMessage("¿Esta seguro de guarda la informacion?")
                .setPositiveButton("Si"){
                    dialog, id -> registerFragmentVM.saveUser(binding.etxtUser.text.toString(), binding.etxtPass.text.toString(),requireContext())
                    dialog.dismiss()
                }.setNegativeButton("No"){
                    dialog, id -> dialog.cancel()
                }.show()
        }
    }

    private fun initObservables(){
        registerFragmentVM.uiState.observe(viewLifecycleOwner){state ->
            manageUISate.invoke(state)
        }
    }

    private fun initVariables(){
        manageUISate = ManageUIState(requireActivity(), binding.lytLoading.pgbarLoadData)
    }
}