package com.example.busbooker.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.busbooker.presentation.mapper.toPresentation
import com.example.busbooker.presentation.model.RouteItemPresentation
import com.example.busbooker.presentation.navigation.Screens
import com.example.busbooker.util.ResourceProvider
import com.example.domain.repository.BusRoutesManager
import com.example.domain.repository.SignInSignUpManager
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class MyRoutesViewModel @Inject constructor(
    private val mRouter: Router,
    private val mSignInSignUpManager: SignInSignUpManager,
    private val mBusRoutesManager: BusRoutesManager,
    private val mResourceProvider: ResourceProvider,
) : ViewModel() {

    private val _shfToastText = MutableSharedFlow<String>(
        replay = 1,
        extraBufferCapacity = 0,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val shfToastText: SharedFlow<String> = _shfToastText

    fun navigateToHome() {
        mRouter.exit()
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
        mRouter.navigateTo(Screens.detailsFragment(item, true))
    }

    fun getMyRvItems(): List<RouteItemPresentation> {

        return mBusRoutesManager.getMyBusRoutes().toPresentation()
    }
}