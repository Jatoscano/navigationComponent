package com.torres.myapplication.logic.usercases.login

import android.content.Context
import com.torres.myapplication.data.local.database.entities.UsersDB
import com.torres.myapplication.data.local.repository.DataBaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.withContext
import java.lang.Exception

class CreateUserWithNameAndPassword(private val context: Context) {

    fun invoke(name: String, password: String) = flow<Boolean>{

            val con = DataBaseRepository.getDBConnection(context)
            con.getUserDao().saveUser(listOf(UsersDB(0, name, password)))
            emit(true)

    }.catch {
        emit(false)
    }
}