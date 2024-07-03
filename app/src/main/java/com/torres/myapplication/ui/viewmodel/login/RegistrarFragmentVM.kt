package com.torres.myapplication.ui.viewmodel.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.torres.myapplication.logic.usercases.login.CreateUserWithNameAndPassword
import com.torres.myapplication.ui.core.UIStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class RegistrarFragmentVM: ViewModel() {

    var uiState = MutableLiveData<UIStates>()
    fun saveUser(name: String, password: String, context: Context){

        viewModelScope.launch(){

            uiState.postValue(UIStates.Loading(true))

            CreateUserWithNameAndPassword(context).invoke(name, password)
                .collect{
                    it.onSuccess { uiState.postValue(UIStates.Success(it)) }
                    it.onFailure { uiState.postValue(UIStates.Error(it.message.toString())) }
                }
            delay(3000)
            uiState.postValue(UIStates.Loading(false))
        }
    }
}