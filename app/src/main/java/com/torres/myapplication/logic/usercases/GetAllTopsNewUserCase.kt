package com.torres.myapplication.logic.usercases

import com.torres.myapplication.data.network.endpoints.NewsEndpoint
import com.torres.myapplication.data.network.repository.RetrofitBase
import com.torres.myapplication.ui.core.toNewsDataUI
import com.torres.myapplication.ui.entities.NewsDataUI

class GetAllTopsNewUserCase {

    suspend operator fun invoke(): Result<List<NewsDataUI>> {

        var items = mutableListOf<NewsDataUI>()

        var response = RetrofitBase.returnBaseRetrofitNews()
            .create(NewsEndpoint::class.java)
            .getAllTopNews()

        return if (response.isSuccessful) {
            response.body()?.data?.forEach {
                items.add(
                    it.toNewsDataUI()
                )
            }
            Result.success(items.toList())
        } else {
            Result.failure(Exception("Ocurrio un error en la API"))
        }
    }
}