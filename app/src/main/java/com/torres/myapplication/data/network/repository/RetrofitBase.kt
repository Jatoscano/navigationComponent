package com.torres.myapplication.data.network.repository

import com.torres.myapplication.data.network.interceptor.MarvelInterceptor
import com.torres.myapplication.data.network.interceptor.NewsInterceptor
import com.torres.myapplication.data.network.repository.ApiKeys.HASH_MARVEL
import com.torres.myapplication.data.network.repository.ApiKeys.NEWS_API_KEY
import com.torres.myapplication.data.network.repository.ApiKeys.TS_MARVEL
import com.torres.myapplication.data.network.repository.ApiKeys.PUBLIC_KEY_MARVEL

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBase {

    fun returnBaseRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun returnBaseRetrofitNews(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.thenewsapi.com/v1/news/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(apiClient())
            .build()
    }

    private fun apiClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(NewsInterceptor(NEWS_API_KEY))
        .build()


    fun returnBaseRetrofitMarvel(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(apiClientMarvel())
            .build()
    }

    private fun apiClientMarvel(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            MarvelInterceptor(
                PUBLIC_KEY_MARVEL,
                TS_MARVEL,
                HASH_MARVEL
            )
        )
        .build()


}