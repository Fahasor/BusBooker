package com.example.busbooker.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.repository.SignInSignUpManager
import com.example.busbooker.presentation.navigation.Screens
import com.example.busbooker.util.ResourceProvider
import com.github.terrakok.cicerone.androidx.FragmentScreen
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mResourceProvider: ResourceProvider,
    private val signInSignUpManager: SignInSignUpManager
) : ViewModel() {
    private val _ldSetStartFragment: MutableLiveData<FragmentScreen> = MutableLiveData()

    val ldSetStartFragment: LiveData<FragmentScreen> = _ldSetStartFragment

    init {
        redirectIfLoggedIn()
    }

    @SuppressLint("CheckResult")
    private fun redirectIfLoggedIn() {
        signInSignUpManager.isUserSignedUp()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isUserSignedUp ->
                _ldSetStartFragment.value = if (isUserSignedUp) {
                    Screens.homeFragment()
                } else {
                    Screens.signInFragment()
                }
            }
    }

}