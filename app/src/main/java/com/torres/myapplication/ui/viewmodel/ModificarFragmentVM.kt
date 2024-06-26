package com.torres.myapplication.ui.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.torres.myapplication.logic.usercases.contador.GetDataAsync
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ModificarFragmentVM: ViewModel() {

    val getDataAsync = GetDataAsync()
    //private var cLive = getDataAsync.invoke().asLiveData()
    //private var cLive = MutableLiveData<Int>()
    var cLive = MutableLiveData<Int>()
    fun funListeners(){


        viewModelScope.launch(Dispatchers.IO) {

            val x = getDataAsync.invoke()

            /*
            x.apply {
                ///Puede desechar datos que no necesito
                distinctUntilChanged()
                //buffer(10)
                map { it + 2 }
                //Si es un dato valido, recolta la informacion
                collect{ cLive.postValue(it) }

            }
             */

            val s = x.map {
                it + 2
            }
            s.collect{
                cLive.postValue(it)
            }
        }

    }
}
