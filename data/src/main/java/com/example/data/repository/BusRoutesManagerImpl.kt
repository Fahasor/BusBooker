package com.example.data.repository

import com.example.data.mapper.toData
import com.example.data.mapper.toDomain
import com.example.data.model.RouteItemData
import com.example.domain.model.RouteItemDomain
import com.example.domain.repository.BusRoutesManager

class BusRoutesManagerImpl : BusRoutesManager {

    private external fun getBusRoutesNative(route: String): List<RouteItemData>

    override fun getBusRoutes(route: String): List<RouteItemDomain> {
        return getBusRoutesNative(route).toDomain();
    }

    override fun getMyBusRoutes(): List<RouteItemDomain> {
        return RoutesDb.myRouteList.toDomain()
    }

    override fun getCitiesRoutes(): List<String> {
        return RoutesDb.routeStringList
    }

    override fun addPassengerOn(route: RouteItemDomain): Boolean {
        return if (route.availableSeatsCount != 0) {
            val repositoryRouteList: MutableList<RouteItemData> = when(route.route) {
                RoutesDb.minskMozyr -> RoutesDb.minskMozyrRouteList
                RoutesDb.mozyrMinsk -> RoutesDb.mozyrMinskRouteList
                else -> return false
            }

            val index = repositoryRouteList.indexOf(route.toData())
            if (index != -1) {
                val newRoute = RouteItemData(
                    imageLink = route.imageLink,
                    time = route.time,
                    dateInDays =route.dateInDays,
                    route = route.route,
                    price = route.price,
                    availableSeatsCount = route.availableSeatsCount - 1,
                )

                for (i in RoutesDb.myRouteList.indices) {
                    if(RoutesDb.myRouteList[i] == route.toData()) {
                        RoutesDb.myRouteList[i] = newRoute
                    }
                }

                repositoryRouteList[index] = newRoute
                RoutesDb.myRouteList.add(newRoute)
                true
            } else {
                false
            }
        } else {
            false
        }
    }

    override fun deletePassengerFrom(route: RouteItemDomain): Boolean {
        val repositoryRouteList: MutableList<RouteItemData> = when(route.route) {
            RoutesDb.minskMozyr -> RoutesDb.minskMozyrRouteList
            RoutesDb.mozyrMinsk -> RoutesDb.mozyrMinskRouteList
            else -> return false
        }

        val index = repositoryRouteList.indexOf(route.toData())
        return if (index != -1) {
            val newRoute = RouteItemData(
                imageLink = route.imageLink,
                time = route.time,
                dateInDays = route.dateInDays,
                route = route.route,
                price = route.price,
                availableSeatsCount = route.availableSeatsCount + 1,
            )
            repositoryRouteList[index] = newRoute
            RoutesDb.myRouteList.remove(route.toData())

            for (i in RoutesDb.myRouteList.indices) {
                if(RoutesDb.myRouteList[i] == route.toData())
                    RoutesDb.myRouteList[i] = newRoute
            }

            true
        }
        else {
            false
        }
    }
}
