package com.example.busbooker.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.busbooker.presentation.mapper.toDomain
import com.example.busbooker.presentation.model.RouteItemPresentation
import com.example.domain.repository.BusRoutesManager
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

class DetailsViewModel @Inject constructor(
    private val mBusRoutesManager: BusRoutesManager,
) : ViewModel() {

    private val _shfToastText = MutableSharedFlow<String>(
        replay = 1,
        extraBufferCapacity = 0,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val shfToastText: SharedFlow<String> = _shfToastText

    fun onConfirmButton(routeItem: RouteItemPresentation, isItOrderedRoute: Boolean) {
        if (isItOrderedRoute) {
            if (mBusRoutesManager.deletePassengerFrom(routeItem.toDomain())) {
                viewModelScope.launch {
                    _shfToastText.emit("Успешно")
                }
            } else {
                viewModelScope.launch {
                    _shfToastText.emit("Ошибка!")
                }
            }
        } else {
            if (mBusRoutesManager.addPassengerOn(routeItem.toDomain())) {
                viewModelScope.launch {
                    _shfToastText.emit("Успешно")
                }
            } else {
                viewModelScope.launch {
                    _shfToastText.emit("Ошибка! Количество свободных мест - 0")
                }
            }
        }
    }

}