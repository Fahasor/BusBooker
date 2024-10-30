package com.example.busbooker.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.busbooker.R
import com.example.busbooker.databinding.FragmentSignUpBinding
import com.example.busbooker.di.injectViewModel
import com.example.busbooker.presentation.viewmodel.SignUpViewModel

class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    private val mViewBinding by viewBinding(FragmentSignUpBinding::bind)
    private val mViewModel: SignUpViewModel by injectViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setViewsPresets()
        setupObservers()
    }

    override fun setViewsPresets() = with(mViewBinding) {

    }

    override fun setupListeners() = with(mViewBinding) {
        tvSignIn.setOnClickListener {
            mViewModel.onSignInBtn()
        }
        btnSignUn.setOnClickListener {
            mViewModel.onSignUpBtn(
                etEmail.text.toString(),
                etPassword.text.toString(),
                etConfirmPassword.text.toString()
            )
        }
    }

    override fun setupObservers() = with(mViewModel) {
        ldShowToastText.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }
}