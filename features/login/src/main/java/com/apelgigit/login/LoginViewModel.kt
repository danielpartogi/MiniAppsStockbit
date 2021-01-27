package com.apelgigit.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.apelgigit.commons.base.BaseViewModel
import com.apelgigit.commons.ext.Resource
import com.apelgigit.commons.thread.DispatcherProvider
import com.apelgigit.data.model.Crypto
import com.apelgigit.data.model.request.CryptoRequest
import com.apelgigit.domain.CryptoUseCase
import kotlinx.coroutines.launch

class LoginViewModel(val dispatcher: DispatcherProvider, val useCase: CryptoUseCase): BaseViewModel(dispatcher) {



}