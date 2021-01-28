package com.apelgigit.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.apelgigit.commons.base.BaseFragment
import com.apelgigit.login.databinding.FragmentSplashBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<SplashViewModel>(SplashViewModel::class) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSplashBinding.inflate(inflater, container, false).root
    }


    override fun onCreated(view: View) {
        lifecycleScope.launch {
            viewModel.isLogin.collect {
                navigationController.navigate(
                    if (!it)
                        SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                    else
                        SplashFragmentDirections.actionSplashFragmentToWatchListFragment()
                )
            }
        }
    }
}