package com.torres.myapplication.ui.entities

import com.torres.myapplication.data.network.entities.topNews.Data

data class NewsDataUI(
    val id: String,
    val url: String,
    val name: String,
    val image: String,
    val description: String,
    val language : String
)


