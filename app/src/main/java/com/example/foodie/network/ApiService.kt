package com.example.foodie.network

import com.example.foodie.data.*
import com.example.foodie.utils.Resource
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

private const val baseUrl = "https://ingenia.app/foodie/ws/"

private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

class ApiService {

    interface ApiService {

        @GET("getUser")
        fun getData(): Call<Users>

        @GET("login")
        suspend fun login(
            @Header("user") user: String,
            @Header("pass") pass: String
        ): LoginResponse

        @GET("getListProducts")
        suspend fun getListProducts() : ListProductsResponse

        @POST("modProduct")
        suspend fun modProduct( @Header("idProducto") idProducto: String,
                                @Header("nomProducto") nomProducto: String,
                                @Header("descProducto") descProducto: String,
                                @Header("precioProducto") precioProducto: String ) : Response?
    }

    object Api {
        val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
    }
}


