package com.apelgigit.login

import com.apelgigit.commons.base.BaseViewModel
import com.apelgigit.commons.constants.Constants
import com.apelgigit.commons.ext.isMoreThan
import com.apelgigit.commons.ext.isValidUsernameAndEmail
import com.apelgigit.commons.utils.DispatcherProvider
import com.apelgigit.domain.CryptoUseCase
import com.apelgigit.domain.LocalUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class LoginViewModel(dispatcher: DispatcherProvider, val useCase: LocalUseCase): BaseViewModel(dispatcher) {

    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }


    val isSubmitEnabled: Flow<Boolean> = combine(_email, _password) { email, password ->
        val isEmailValid = email.isValidUsernameAndEmail()
        val isPasswordCorrect = password.isMoreThan(Constants.MININIMUM_PASSWORD)
        return@combine isEmailValid and isPasswordCorrect
    }

    fun setIsLogin(){
        useCase.setIsLogin(true)
    }
}