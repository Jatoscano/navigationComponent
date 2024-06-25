package com.torres.myapplication.ui.core

import com.torres.myapplication.data.network.entities.topNews.Data
import com.torres.myapplication.ui.entities.NewsDataUI

class FunctionExtensions

fun Data.toNewsDataUI() =
    NewsDataUI(
        this.uuid,
        this.url,
        this.title,
        this.image_url,
        this.description,
        this.language
    )


