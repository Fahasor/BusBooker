package com.example.busbooker.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.domain.repository.SignInSignUpManager
import com.example.busbooker.presentation.mapper.toPresentation
import com.example.busbooker.presentation.model.RouteItemPresentation
import com.example.busbooker.presentation.navigation.Screens
import com.example.domain.repository.BusRoutesManager
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val mRouter: Router,
    private val mBusRoutesManager: BusRoutesManager,
    private val mSignInSignUpManager: SignInSignUpManager,
) : ViewModel() {

    private val _sfSpinnerRoutes: MutableStateFlow<List<String>> = MutableStateFlow(listOf())
    private val _sfSelectedRoute: MutableStateFlow<String> = MutableStateFlow("")

    val sfSpinnerRoutes: StateFlow<List<String>> = _sfSpinnerRoutes
    val sfSelectedRoute: StateFlow<String> = _sfSelectedRoute

    init {
        _sfSpinnerRoutes.value = mBusRoutesManager.getCitiesRoutes()
    }

    fun navigateToAddNote() {
        mRouter.navigateTo(Screens.myRoutesFragment())
    }

    @SuppressLint("CheckResult")
    fun onLogout() {
        mSignInSignUpManager.logOutUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isLogoutSuccessful ->
                if(isLogoutSuccessful) {
                    mRouter.newRootScreen(Screens.signInFragment())
                }
            }
    }

    fun onClickItem(item: RouteItemPresentation) {
        mRouter.navigateTo(Screens.detailsFragment(item, false))
    }

    fun getRvItems(route: String): List<RouteItemPresentation> {
        return mBusRoutesManager.getBusRoutes(route).toPresentation()
    }

    fun changeSelectedRouteOn(selectedItem: String) {
        _sfSelectedRoute.value = selectedItem
    }
}