package com.torres.myapplication.data.network.endpoints

import com.torres.myapplication.data.network.entities.oneNews.OneNewsDataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UUIDNews {

    @GET("uuid/{uuid}")
    suspend fun getUUIDNews(@Path("uuid") uuid: String): Response<OneNewsDataClass>

}
