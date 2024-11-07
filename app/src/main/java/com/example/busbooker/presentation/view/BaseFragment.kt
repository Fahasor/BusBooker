package com.example.busbooker.presentation.view

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(
    @LayoutRes private val layoutResId: Int
) : Fragment(layoutResId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setViewsPresets()
        setupObservers()
    }

    abstract fun setViewsPresets(): Unit

    abstract fun setupListeners(): Unit

    abstract fun setupObservers(): Unit
}