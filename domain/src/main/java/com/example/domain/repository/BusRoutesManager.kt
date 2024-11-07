package com.example.domain.repository

import com.example.domain.model.RouteItemDomain

interface BusRoutesManager {

    fun getBusRoutes(route: String): List<RouteItemDomain>

    fun getMyBusRoutes(): List<RouteItemDomain>

    fun getCitiesRoutes(): List<String>

    fun addPassengerOn(route: RouteItemDomain): Boolean

    fun deletePassengerFrom(route: RouteItemDomain): Boolean
}