package com.torres.myapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.torres.myapplication.R
import com.torres.myapplication.data.local.database.entities.UsersDB
import com.torres.myapplication.data.local.repository.DataBaseRepository
import com.torres.myapplication.databinding.FragmentRegisterBinding
import com.torres.myapplication.logic.usercases.login.CreateUserWithNameAndPassword
import com.torres.myapplication.ui.core.MyApplication
import com.torres.myapplication.ui.viewmodel.login.RegistrarFragmentVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private lateinit var con: DataBaseRepository

    private val registerFragmentVM: RegistrarFragmentVM by viewModels()

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

        initListeners()
        initObservables()
    }

    private fun initListeners(){
        binding.btnBack.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }

        binding.btnSave.setOnClickListener {

            lifecycleScope.launch{
                registerFragmentVM.saveUser(binding.etxtUser.text.toString(), binding.etxtPass.text.toString(),requireContext())
            }
        }
    }

    private fun initObservables(){
        registerFragmentVM.userSaved.observe(viewLifecycleOwner){ res ->
            if (res){
                binding.etxtUser.setText("")
                binding.etxtPass.setText("")
                Snackbar.make(binding.etxtUser, "Usuario Ingresado Exitosamente", Snackbar.LENGTH_LONG).show()
            }

            else{
                Snackbar.make(binding.etxtUser, "Ocurrio un Error", Snackbar.LENGTH_LONG).show()
            }
        }
    }

}