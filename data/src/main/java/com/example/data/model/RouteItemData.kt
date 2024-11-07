package com.example.data.model

data class RouteItemData(
    val imageLink: String,
    val time: String,
    val dateInDays: Int,
    val route: String,
    val price: Int,
    val availableSeatsCount: Int,
)