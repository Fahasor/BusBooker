package com.example.data.mapper

import com.example.data.model.RouteItemData
import com.example.domain.model.RouteItemDomain

fun RouteItemData.toDomain(): RouteItemDomain {
    return RouteItemDomain(
        imageLink = this.imageLink,
        time = this.time,
        dateInDays = this.dateInDays,
        route = this.route,
        price = this.price,
        availableSeatsCount = this.availableSeatsCount,
    )
}

fun RouteItemDomain.toData(): RouteItemData {
    return RouteItemData(
        imageLink = this.imageLink,
        time = this.time,
        dateInDays = this.dateInDays,
        route = this.route,
        price = this.price,
        availableSeatsCount = this.availableSeatsCount
    )
}

fun List<RouteItemData>.toDomain(): List<RouteItemDomain> {
    return this.map { it.toDomain() }
}

fun List<RouteItemDomain>.toData(): List<RouteItemData> {
    return this.map { it.toData() }
}