package com.torres.myapplication.logic.usercases.marvel

import android.util.Log
import com.torres.myapplication.data.network.endpoints.MarvelEndpoint
import com.torres.myapplication.data.network.endpoints.UUIDNews
import com.torres.myapplication.data.network.entities.marvel.characters.MarvelChars
import com.torres.myapplication.data.network.entities.oneNews.OneNewsDataClass
import com.torres.myapplication.data.network.repository.RetrofitBase
import com.torres.myapplication.ui.core.toMarvelCharsUI
import com.torres.myapplication.ui.entities.MarvelsCharsUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn

/*
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
 */

class GetMarvelCharsUserCase {

    suspend operator fun invoke() = flow {

        var response = RetrofitBase.returnBaseRetrofitMarvel()
            .create(MarvelEndpoint::class.java)
            .getAllcharacters(2)

        if (response.isSuccessful) {
            val x = response.body()?.data?.results
            val items = ArrayList<MarvelsCharsUI>()

            x?.forEach { items.add(it.toMarvelCharsUI()) }
            Log.d("TAG", items.size.toString())
            emit(Result.success(items.toList()))
        }
    }.catch {
        Log.d("TAG", it.message.toString())
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)
}