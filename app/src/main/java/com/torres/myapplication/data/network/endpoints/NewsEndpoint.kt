package com.torres.myapplication.data.network.endpoints

import com.torres.myapplication.data.network.entities.allNews.AllNews
import com.torres.myapplication.data.network.entities.topNews.NewsApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsEndpoint {

    @GET("{user_id}/top")
    suspend fun getAllTopNews(): Response<NewsApi>

    @GET("all")
    suspend fun getAllNews(): Response<AllNews>

}