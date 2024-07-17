package com.example.api.data

import com.example.api.data.model.Product
import kotlinx.coroutines.flow.Flow


interface Repository {
    suspend fun getProductsList(): Flow<Result<List<Product>>>
    }

