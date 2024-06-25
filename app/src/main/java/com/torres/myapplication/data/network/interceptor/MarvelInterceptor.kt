package com.torres.myapplication.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class MarvelInterceptor(
    private val publicKey: String,
    private val ts: Int,
    private val hash: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("ts", ts.toString())
            .addQueryParameter("hash", hash)
            .build()
        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)

    }
}