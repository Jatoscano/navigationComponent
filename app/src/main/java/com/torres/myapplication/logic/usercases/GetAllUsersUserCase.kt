package com.torres.myapplication.logic.usercases

import android.util.Log
import com.torres.myapplication.data.network.endpoints.UsersEndpoint
import com.torres.myapplication.data.network.repository.RetrofitBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllUsersUserCase {

    suspend operator fun invoke(): Boolean {

        return withContext(Dispatchers.IO) {

            val call = RetrofitBase.returnBaseRetrofit()
            val service = call.create(UsersEndpoint::class.java)
            val response = service.getAllUsers()

            return@withContext if (response.isSuccessful) {
                val body = response.body()
                Log.d("RSP", body.toString())
                true

            } else {
                Log.d("RSP", "La ejecucion fallo")
                false
            }
        }

    }


}