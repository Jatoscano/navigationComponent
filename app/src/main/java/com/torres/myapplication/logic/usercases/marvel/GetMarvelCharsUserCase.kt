package com.torres.myapplication.logic.usercases.marvel

import com.torres.myapplication.data.network.endpoints.MarvelEndpoint
import com.torres.myapplication.data.network.endpoints.UUIDNews
import com.torres.myapplication.data.network.entities.marvel.characters.MarvelChars
import com.torres.myapplication.data.network.entities.oneNews.OneNewsDataClass
import com.torres.myapplication.data.network.repository.RetrofitBase

class GetMarvelCharsUserCase {

    suspend operator fun invoke(): Result<List<com.torres.myapplication.data.network.entities.marvel.characters.Result>?> {

        var response = RetrofitBase.returnBaseRetrofitMarvel()
            .create(MarvelEndpoint::class.java)
            .getAllcharacters(2)

        return if (response.isSuccessful) {
            val x = response.body()?.data?.results
            Result.success(x)
        } else {
            Result.failure(Exception("Ocurrio un error en la API"))
        }
    }
}