package com.torres.myapplication.ui.viewmodel.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.torres.myapplication.logic.usercases.marvel.GetMarvelCharsUserCase
import com.torres.myapplication.ui.entities.MarvelsCharsUI
import kotlinx.coroutines.launch

class ListMarvelCharsVM: ViewModel() {

    val itemMarvel = MutableLiveData<List<MarvelsCharsUI>>()

    fun initData(){

        viewModelScope.launch{
            GetMarvelCharsUserCase().invoke().collect{
                    marvel -> marvel.onSuccess {
                    items -> itemMarvel.postValue(items)
                Log.d("TAG", "Ingresando...")
                    }
                    marvel.onFailure {
                        Log.d("TAG", it.message.toString())
                    }
            }
        }
    }
}