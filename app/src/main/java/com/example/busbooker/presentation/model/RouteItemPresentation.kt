package com.example.busbooker.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RouteItemPresentation(
    val imageLink: String,
    val time: String,
    val dateInDays: Int,
    val route: String,
    val price: Int,
    val availableSeatsCount: Int,
) : Parcelable