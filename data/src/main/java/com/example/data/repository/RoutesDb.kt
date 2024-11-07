package com.example.data.repository

import com.example.data.model.RouteItemData

class RoutesDb {
    companion object {
        const val minskMozyr = "Минск-Мозырь"
        const val mozyrMinsk = "Мозырь-Минск"

        const val blackBusUrl =
            "https://v-orshy.by/wp-content/uploads/2016/12/main-mikroautobus.jpg"
        const val redBusUrl =
            "https://redcarsto.by/upload/iblock/8cf/8cf5858dd520225ba507b2dc0867f1a8.jpg"
        const val whiteBusUrl =
            "https://avto-slava.by/files/cache/5b/31/5b317e14b47cf42d2ba8b507888fc216.jpg"

        val routeStringList = mutableListOf(mozyrMinsk, minskMozyr)

        val myRouteList = mutableListOf<RouteItemData>()

        val minskMozyrRouteList = mutableListOf(
            RouteItemData(
                imageLink = blackBusUrl,
                time = "12.45-16.30",
                dateInDays = 20020,
                route = minskMozyr,
                price = 29,
                availableSeatsCount = 0
            ),
            RouteItemData(
                imageLink = whiteBusUrl,
                time = "16.45-20.30",
                dateInDays = 20021,
                route = minskMozyr,
                price = 30,
                availableSeatsCount = 4
            ),
            RouteItemData(
                imageLink = redBusUrl,
                time = "18.45-22.30",
                dateInDays = 20021,
                route = minskMozyr,
                price = 30,
                availableSeatsCount = 9
            )
        )

        val mozyrMinskRouteList = mutableListOf(
            RouteItemData(
                imageLink = whiteBusUrl,
                time = "03.15-08.25",
                dateInDays = 20032,
                route = mozyrMinsk,
                price = 27,
                availableSeatsCount = 13
            ),
            RouteItemData(
                imageLink = whiteBusUrl,
                time = "16.45-20.30",
                dateInDays = 20033,
                route = mozyrMinsk,
                price = 28,
                availableSeatsCount = 2
            ),
            RouteItemData(
                imageLink = blackBusUrl,
                time = "18.45-22.30",
                dateInDays = 20034,
                route = mozyrMinsk,
                price = 28,
                availableSeatsCount = 5
            ),
            RouteItemData(
                imageLink = blackBusUrl,
                time = "18.45-22.30",
                dateInDays = 20035,
                route = mozyrMinsk,
                price = 28,
                availableSeatsCount = 5
            ),
            RouteItemData(
                imageLink = whiteBusUrl,
                time = "18.45-22.30",
                dateInDays = 20036,
                route = mozyrMinsk,
                price = 28,
                availableSeatsCount = 5
            ),
            RouteItemData(
                imageLink = blackBusUrl,
                time = "18.45-22.30",
                dateInDays = 20037,
                route = mozyrMinsk,
                price = 28,
                availableSeatsCount = 5
            ),
            RouteItemData(
                imageLink = redBusUrl,
                time = "19.45-23.30",
                dateInDays = 20037,
                route = mozyrMinsk,
                price = 28,
                availableSeatsCount = 5
            ),
            RouteItemData(
                imageLink = redBusUrl,
                time = "19.45-23.30",
                dateInDays = 20037,
                route = mozyrMinsk,
                price = 28,
                availableSeatsCount = 5
            ),
            RouteItemData(
                imageLink = redBusUrl,
                time = "19.45-23.30",
                dateInDays = 20037,
                route = mozyrMinsk,
                price = 28,
                availableSeatsCount = 5
            )
        )
    }
}