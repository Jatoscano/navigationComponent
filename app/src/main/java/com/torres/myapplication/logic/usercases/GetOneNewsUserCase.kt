package com.torres.myapplication.logic.usercases

import com.torres.myapplication.data.network.endpoints.UUIDNews
import com.torres.myapplication.data.network.entities.oneNews.OneNewsDataClass
import com.torres.myapplication.data.network.repository.RetrofitBase

class GetOneNewsUserCase {

    suspend operator fun invoke(uuid: String): Result<OneNewsDataClass?> {

        var response = RetrofitBase.returnBaseRetrofitNews()
            .create(UUIDNews::class.java)
            .getUUIDNews(uuid)

        return if (response.isSuccessful) {
            val x = response.body()
            Result.success(x)
        } else {
            Result.failure(Exception("Ocurrio un error en la API"))
        }
    }
}