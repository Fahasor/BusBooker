package com.example.data.repository

import android.util.Log
import android.widget.Toast
import com.example.data.mapper.toData
import com.example.data.mapper.toDomain
import com.example.data.model.RouteItemData
import com.example.domain.model.RouteItemDomain
import com.example.domain.repository.BusRoutesManager
import org.jetbrains.annotations.Debug

class BusRoutesManagerImpl : BusRoutesManager {

    override fun getBusRoutes(route: String): List<RouteItemDomain> {
        return if (route == minskMozyr) {
            minskMozyrRouteList.toDomain()
        } else {
            mozyrMinskRouteList.toDomain()
        }
    }

    override fun getMyBusRoutes(): List<RouteItemDomain> {
        return myRouteList.toDomain()
    }

    override fun getCitiesRoutes(): List<String> {
        return routeStringList
    }

    override fun addPassengerOn(route: RouteItemDomain): Boolean {
        return if (route.availableSeatsCount != 0) {
            val repositoryRouteList: MutableList<RouteItemData> = when(route.route) {
                minskMozyr -> minskMozyrRouteList
                mozyrMinsk -> mozyrMinskRouteList
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

                for (i in myRouteList.indices) {
                    if(myRouteList[i] == route.toData()) {
                        myRouteList[i] = newRoute
                    }
                }

                repositoryRouteList[index] = newRoute
                myRouteList.add(newRoute)
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
            minskMozyr -> minskMozyrRouteList
            mozyrMinsk -> mozyrMinskRouteList
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
            myRouteList.remove(route.toData())

            for (i in myRouteList.indices) {
                if(myRouteList[i] == route.toData())
                    myRouteList[i] = newRoute
            }

            true
        }
        else {
            false
        }
    }
}
