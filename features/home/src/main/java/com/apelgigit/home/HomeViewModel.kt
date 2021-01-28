package com.apelgigit.home

import com.apelgigit.commons.base.BaseViewModel
import com.apelgigit.commons.utils.DispatcherProvider
import com.apelgigit.domain.LocalUseCase

class HomeViewModel(dispatcherProvider: DispatcherProvider,
                    private val useCase: LocalUseCase
): BaseViewModel(dispatcherProvider) {

    fun setIsLogin(){
        useCase.setIsLogin(false)
    }
}