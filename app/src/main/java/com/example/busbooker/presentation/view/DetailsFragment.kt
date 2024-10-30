package com.example.busbooker.presentation.view

import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.busbooker.R
import com.example.busbooker.databinding.FragmentDetailsBinding
import com.example.busbooker.di.injectViewModel
import com.example.busbooker.presentation.model.RouteItemPresentation
import com.example.busbooker.presentation.navigation.IS_IT_ORDERED_ROUTE
import com.example.busbooker.presentation.navigation.ROUTE_ITEM_ARGUMENT_KEY
import com.example.busbooker.presentation.viewmodel.DetailsViewModel
import com.example.busbooker.util.getArgument
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.time.LocalDate

class DetailsFragment : BaseFragment(R.layout.fragment_details) {

    private val mViewBinding by viewBinding(FragmentDetailsBinding::bind)
    private val mViewModel: DetailsViewModel by injectViewModel()

    private val mRouteItem: RouteItemPresentation by lazy (LazyThreadSafetyMode.NONE) {
        getArgument(ROUTE_ITEM_ARGUMENT_KEY) }

    private val mIsItOrderedRoute: Boolean by lazy (LazyThreadSafetyMode.NONE) {
        getArgument(IS_IT_ORDERED_ROUTE) }

    override fun setViewsPresets() = with(mViewBinding) {
        Picasso.get()
            .load(mRouteItem.imageLink)
            .placeholder(R.drawable.im_flat_placeholder)
            .into(pvPhoto)

        tvDate.text = LocalDate.ofEpochDay(mRouteItem.dateInDays.toLong()).toString()
        tvTime.text = mRouteItem.time
        tvRoute.text = mRouteItem.route
        tvPrice.text = mRouteItem.price.toString()
        tvAvailableSeatsCount.text = getString(R.string.available_seats_count) +
                mRouteItem.availableSeatsCount.toString()

        bConfirm.text = if (mIsItOrderedRoute) {
            getString(R.string.delete)
        } else {
            getString(R.string.confirm)
        }
    }

    override fun setupListeners(): Unit = with(mViewBinding) {
        bConfirm.setOnClickListener {
            mViewModel.onConfirmButton(mRouteItem, mIsItOrderedRoute)
        }
    }

    override fun setupObservers(): Unit = with(mViewModel) {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                shfToastText.collect { text ->
                    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}