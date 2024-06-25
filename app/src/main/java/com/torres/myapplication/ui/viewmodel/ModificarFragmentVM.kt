package com.torres.myapplication.ui.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ModificarFragmentVM: ViewModel() {

    var cLive = MutableLiveData<Int>()
            private var c = 1
    fun funListeners(){
        c += 1
        cLive.postValue(c)
    }
}
