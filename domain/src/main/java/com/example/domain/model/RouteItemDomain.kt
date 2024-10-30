package com.example.domain.model

data class RouteItemDomain(
    val imageLink: String,
    val time: String,
    val dateInDays: Int,
    val route: String,
    val price: Int,
    val availableSeatsCount: Int,
)