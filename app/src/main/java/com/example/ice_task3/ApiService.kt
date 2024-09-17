package com.example.ice_task3

import retrofit2.http.GET
import retrofit2.Call

interface ApiService {
    @GET("api/data")
    fun getData(): Call<ApiResponse>
}