package com.example.busbooker.presentation.view

import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.busbooker.R
import com.example.busbooker.databinding.FragmentMyRoutesBinding
import com.example.busbooker.di.injectViewModel
import com.example.busbooker.presentation.adapter.RvItemsAdapter
import com.example.busbooker.presentation.model.RouteItemPresentation
import com.example.busbooker.presentation.viewmodel.MyRoutesViewModel
import kotlinx.coroutines.launch

class MyRoutesFragment : BaseFragment(R.layout.fragment_my_routes), RvItemsAdapter.ClickListener {

    private val mViewBinding by viewBinding(FragmentMyRoutesBinding::bind)
    private val mViewModel: MyRoutesViewModel by injectViewModel()
    private val mRvAdapter: RvItemsAdapter = RvItemsAdapter(this)

    override fun setViewsPresets(): Unit = with(mViewBinding) {
        rvList.adapter = mRvAdapter
        mRvAdapter.addHouseItems(mViewModel.getMyRvItems())
    }

    override fun setupListeners(): Unit = with(mViewBinding) {
        ivHome.setOnClickListener {
            mViewModel.navigateToHome()
        }
        fabLogout.setOnClickListener {
            mViewModel.onLogout()
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
    override fun onClickRvItem(item: RouteItemPresentation) {
        mViewModel.onClickItem(item)
    }
}