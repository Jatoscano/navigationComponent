package com.torres.myapplication.logic.usercases.contador

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow


class GetDataAsync {

    private var c = 1
    //Consturccion Normal
    /*
    suspend fun invoke(): Int{

        while (true){
            delay(5000)
            c += 1
        }
        return c
    }
     */

    //Construccion a Flow
    fun invoke() = flow{

        while (true){
            c += 1
            emit(c)
            delay(5000)
        }
    }
}