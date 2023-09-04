package com.example.multi_platform_android_project.android.Helper

import androidx.compose.runtime.Composable
import com.example.multi_platform_android_project.ApiService

@Composable
fun NetworkScreen(){


    val ad = ApiService().build()
    ad
    get(urlString = "${AppKey.BASE_URL}$url") {
        contentType(ContentType.Application.Json)
        parameter("api_key", ApiKey().value)
        parameter("page", page)
    }
}