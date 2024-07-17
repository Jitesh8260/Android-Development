package com.example.api.data

import com.example.api.Rating

data class Post(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)