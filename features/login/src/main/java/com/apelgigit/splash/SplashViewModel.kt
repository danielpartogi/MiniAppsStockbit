package com.apelgigit.splash

import com.apelgigit.commons.base.BaseViewModel
import com.apelgigit.commons.utils.DispatcherProvider
import com.apelgigit.domain.LocalUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class SplashViewModel(dispatcher: DispatcherProvider, val useCase: LocalUseCase): BaseViewModel(dispatcher) {

    private val _islogin: MutableStateFlow<Boolean> = MutableStateFlow(useCase.getIsLogin())
    val isLogin: StateFlow<Boolean> get() = _islogin
}