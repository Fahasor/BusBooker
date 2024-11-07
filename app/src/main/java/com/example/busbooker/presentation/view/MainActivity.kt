package com.example.busbooker.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.busbooker.App
import com.example.busbooker.R
import com.example.busbooker.databinding.ActivityMainBinding
import com.example.busbooker.di.injectViewModel
import com.example.busbooker.presentation.viewmodel.MainViewModel
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val mViewBinding by viewBinding(ActivityMainBinding::bind)
    private val mViewModel: MainViewModel by injectViewModel()

    @Inject
    lateinit var mNavigatorHolder: NavigatorHolder

    companion object {
      init {
         System.loadLibrary("main")
      }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        setContentView(R.layout.activity_main)
        setupObservers(savedInstanceState)
    }

    private val mNavigator: Navigator = object : AppNavigator(this, R.id.container) {
        override fun applyCommands(commands: Array<out Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        mNavigatorHolder.setNavigator(mNavigator)
    }

    override fun onPause() {
        mNavigatorHolder.removeNavigator()
        super.onPause()
    }

    private fun setupObservers(savedInstanceState: Bundle?): Unit = with(mViewBinding) {
        if (savedInstanceState == null) {
            mViewModel.ldSetStartFragment.observe(this@MainActivity) {
                mNavigator.applyCommands(arrayOf<Command>(Replace(it)))
            }
        }
    }

}