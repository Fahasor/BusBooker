package com.example.busbooker.presentation.navigation

import com.example.busbooker.presentation.model.RouteItemPresentation
import com.example.busbooker.presentation.view.MyRoutesFragment
import com.example.busbooker.presentation.view.DetailsFragment
import com.example.busbooker.presentation.view.HomeFragment
import com.example.busbooker.presentation.view.SignInFragment
import com.example.busbooker.presentation.view.SignUpFragment
import com.example.busbooker.util.withArguments
import com.github.terrakok.cicerone.androidx.FragmentScreen
const val IS_IT_ORDERED_ROUTE = "is_it_ordered_route"
const val ROUTE_ITEM_ARGUMENT_KEY = "bus_item"

object Screens {
    fun signInFragment() = FragmentScreen { SignInFragment() }
    fun signUpFragment() = FragmentScreen { SignUpFragment() }
    fun homeFragment() = FragmentScreen { HomeFragment() }
    fun detailsFragment(item: RouteItemPresentation, isItOrderedRoute: Boolean) = FragmentScreen {
        DetailsFragment().withArguments(
        ROUTE_ITEM_ARGUMENT_KEY to item,
        IS_IT_ORDERED_ROUTE to isItOrderedRoute,
        )
    }
    fun myRoutesFragment() = FragmentScreen { MyRoutesFragment() }

}