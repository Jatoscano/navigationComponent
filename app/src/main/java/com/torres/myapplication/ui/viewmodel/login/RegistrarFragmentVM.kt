package com.torres.myapplication.ui.viewmodel.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.torres.myapplication.logic.usercases.login.CreateUserWithNameAndPassword
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class RegistrarFragmentVM: ViewModel() {

    var userSaved = MutableLiveData<Boolean>()
    suspend fun saveUser(name: String, password: String, context: Context){

        val user = CreateUserWithNameAndPassword(context).invoke(name, password)
        user.apply {
            launchIn(scope = viewModelScope)
            collect{
                userSaved.postValue(it)
            }
        }
        userSaved.postValue(true)
    }
}