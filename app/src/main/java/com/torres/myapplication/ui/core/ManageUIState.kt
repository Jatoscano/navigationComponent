package com.torres.myapplication.ui.core

import android.content.Context
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ManageUIState(private val context: Context, private val view: View){
    fun invoke(state: UIStates){

        when(state){
            is UIStates.Success -> {

                MaterialAlertDialogBuilder(context)
                    .setTitle("Correcto")
                    .setMessage("La operacion fue extiosa")
                    .setPositiveButton("Aceptat"){dialog, id ->
                        dialog.dismiss()
                    }
                    .show()}
            is UIStates.Loading -> {

                if (state.isLoading){
                     view.visibility= View.VISIBLE
                }
                else{
                    view.visibility = View.GONE
                }
            }
            is UIStates.Error -> {

                MaterialAlertDialogBuilder(context)
                    .setTitle("Error")
                    .setMessage(state.message)
                    .setPositiveButton("Aceptat"){dialog, id ->
                        dialog.dismiss()
                    }
                    .show() }
            is UIStates.Alert -> { }
        }
    }
}