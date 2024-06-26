package com.torres.myapplication.ui.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import com.torres.myapplication.logic.usercases.GetAllTopsNewUserCase
import com.torres.myapplication.ui.entities.NewsDataUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListarNewVM: ViewModel() {

    val listNesData = MutableLiveData<List<NewsDataUI>>()
    val error = MutableLiveData<String>()
    fun initData() {

        viewModelScope.launch(Dispatchers.IO) {
            val result = GetAllTopsNewUserCase().invoke()
            withContext(Dispatchers.Main) {


                result.collect {res ->
                    res.onSuccess {
                        listNesData.postValue(it)
                    }

                    res.onFailure {
                      error.postValue(it.message)
                    }

                }

            }
        }
    }
}