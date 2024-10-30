package com.example.busbooker.presentation.mapper

import com.example.domain.model.RouteItemDomain
import com.example.busbooker.presentation.model.RouteItemPresentation

fun RouteItemPresentation.toDomain(): RouteItemDomain {
    return RouteItemDomain(
        imageLink = this.imageLink,
        time = this.time,
        dateInDays = this.dateInDays,
        route = this.route,
        price = this.price,
        availableSeatsCount = this.availableSeatsCount,
    )
}

fun RouteItemDomain.toPresentation(): RouteItemPresentation {
    return RouteItemPresentation(
        imageLink = this.imageLink,
        time = this.time,
        dateInDays = this.dateInDays,
        route = this.route,
        price = this.price,
        availableSeatsCount = this.availableSeatsCount,
    )
}

fun List<RouteItemPresentation>.toDomain(): List<RouteItemDomain> {
    return this.map { it.toDomain() }
}

fun List<RouteItemDomain>.toPresentation(): List<RouteItemPresentation> {
    return this.map { it.toPresentation() }
}