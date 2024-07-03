package com.torres.myapplication.logic.usercases.login

import android.content.Context
import com.torres.myapplication.data.local.database.entities.UsersDB
import com.torres.myapplication.data.local.repository.DataBaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.withContext
import java.lang.Exception

class CreateUserWithNameAndPassword(private val context: Context) {

    fun invoke(name: String, password: String) = flow{

        kotlinx.coroutines.delay(3000)
        DataBaseRepository.getDBConnection(context)
            .getUserDao().saveUser(listOf(UsersDB(0, name, password)))
            emit(Result.success(true))

    }.catch {ex ->
        emit(Result.failure(ex))
    }.flowOn(Dispatchers.IO)
}