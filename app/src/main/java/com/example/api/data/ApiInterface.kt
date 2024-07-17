package com.example.api.data

import com.example.api.data.model.Product
import retrofit2.http.GET

interface ApiInterface {
    @GET("/products")
    suspend fun getProductsList(): List<Product>
    companion object {
        const val BASE_URL = "https://fakestoreapi.com"
    }
}