package com.torres.myapplication.data.network.endpoints

import com.torres.myapplication.data.network.entities.marvel.characters.MarvelChars
import com.torres.myapplication.data.network.entities.topNews.NewsApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelEndpoint {

    @GET("v1/public/characters")
    suspend fun getAllcharacters(
        @Query("limit") limit: Int
    ): Response<MarvelChars>

}