package com.example.busbooker.presentation.view

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.busbooker.R
import com.example.busbooker.databinding.FragmentHomeBinding
import com.example.busbooker.di.injectViewModel
import com.example.busbooker.presentation.adapter.RvItemsAdapter
import com.example.busbooker.presentation.model.RouteItemPresentation
import com.example.busbooker.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment(R.layout.fragment_home), RvItemsAdapter.ClickListener {

    private val mViewBinding by viewBinding(FragmentHomeBinding::bind)
    private val mViewModel: HomeViewModel by injectViewModel()
    private val mRvAdapter: RvItemsAdapter = RvItemsAdapter(this)

    override fun setViewsPresets(): Unit = with(mViewBinding) {
        rvList.adapter = mRvAdapter
    }

    override fun setupListeners(): Unit = with(mViewBinding) {
        ivAddNote.setOnClickListener {
            mViewModel.navigateToAddNote()
        }
        fabLogout.setOnClickListener {
            mViewModel.onLogout()
        }
        mViewBinding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                mViewModel.changeSelectedRouteOn(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    override fun setupObservers(): Unit = with(mViewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                mViewModel.sfSpinnerRoutes.collect { routes ->
                    val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, routes)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    mViewBinding.spinner?.adapter = adapter
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                mViewModel.sfSelectedRoute.collect { route ->
                    mRvAdapter.addHouseItems(mViewModel.getRvItems(route))
                }
            }
        }
    }

    override fun onClickRvItem(item: RouteItemPresentation) {
        mViewModel.onClickItem(item)
    }
}