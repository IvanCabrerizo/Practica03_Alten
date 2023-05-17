package com.example.practicas03.data

import com.example.practicas03.data.model.WebBrowserDto
import retrofit2.Call
import retrofit2.http.GET

interface WebBrowserApiService {
    @GET("v1/533d9807-d64d-469b-8e39-a06317d22007")
    fun getWebBrowsers(): Call<List<WebBrowserDto>>
}