package com.torres.myapplication.ui.viewmodel.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.torres.myapplication.logic.usercases.login.CreateUserWithNameAndPassword
import com.torres.myapplication.ui.core.UIStates
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragmentVM: ViewModel() {

    var uiState = MutableLiveData<UIStates>()
    var idUser = MutableLiveData<Int>()

    fun getUserFromDB(name: String, password: String, context: Context){
       viewModelScope.launch {
           uiState.postValue(UIStates.Loading(true))

           CreateUserWithNameAndPassword(context).invoke(name, password).collect{
               it.onSuccess {
                   idUser.postValue(1)
               }
               it.onFailure {
                   uiState.postValue(UIStates.Error(it.message.toString()))
               }
           }

           delay(500)
           uiState.postValue(UIStates.Loading(false))
       }
    }
}